package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionNotFinishedException;
import com.dbserver.desafiovotacao.core.exceptions.GenericAgendaException;
import com.dbserver.desafiovotacao.core.usecases.agenda.CountAgendaVotes;
import com.dbserver.desafiovotacao.core.usecases.agenda.CreateAgenda;
import com.dbserver.desafiovotacao.core.usecases.agenda.ListAgendas;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    private final PostgresAgendaRepository repository;

    /**
     * Constructs a new {@link AgendaService} with the specified {@link PostgresAgendaRepository} dependency.
     *
     * @param repository The {@link PostgresAgendaRepository} used to interact with agenda entities.
     */
    public AgendaService(PostgresAgendaRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new agenda.
     *
     * @param dto The {@link AgendaDTO} representing the agenda to be created.
     * @return The created {@link AgendaDTO}.
     */
    public AgendaDTO create(AgendaDTO dto) {
        this.canCreate(dto);

        if (dto.getStatus() == null) {
            dto.setStatus(EnumAgendaSessionStatus.CREATED);
        }

        CreateAgenda createAgenda = new CreateAgenda(repository);

        return createAgenda.execute(dto);
    }

    private void canCreate(AgendaDTO dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new GenericAgendaException("Missing required property 'title'", 400);
        }
    }

    /**
     * Retrieves a list of all agendas.
     *
     * @return A list of {@link AgendaDTO} representing all agendas.
     */
    public List<AgendaDTO> list() {
        ListAgendas listAgendas = new ListAgendas(repository);

        return listAgendas.execute();
    }

    /**
     * Counts the votes for a specific agenda.
     *
     * @param agendaId The ID of the agenda.
     * @return The {@link AgendaResultDTO} containing the vote counts.
     * @throws AgendaSessionNotFinishedException if the session for the agenda is not finished.
     */
    public AgendaResultDTO countVotes(String agendaId) throws AgendaSessionNotFinishedException {
        if (!this.isSessionFinished(agendaId)) {
            throw new AgendaSessionNotFinishedException();
        }

        CountAgendaVotes countAgendaVotes = new CountAgendaVotes(repository);

        return countAgendaVotes.execute(agendaId);
    }

    /**
     * Checks if the session for the specified agenda is finished.
     *
     * @param agendaId The ID of the agenda.
     * @return True if the session is finished, false otherwise.
     */
    private boolean isSessionFinished(String agendaId) {
        AgendaDTO agenda = this.repository.findById(agendaId)
                .orElseThrow(AgendaNotFoundException::new);

        return agenda.getStatus() == EnumAgendaSessionStatus.CLOSED;
    }

}
