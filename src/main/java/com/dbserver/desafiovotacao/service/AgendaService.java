package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionNotFinishedException;
import com.dbserver.desafiovotacao.core.usecases.agenda.CountAgendaVotes;
import com.dbserver.desafiovotacao.core.usecases.agenda.CreateAgenda;
import com.dbserver.desafiovotacao.core.usecases.agenda.ListAgendas;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    private final PostgresAgendaRepository repository;

    public AgendaService(PostgresAgendaRepository repository) {
        this.repository = repository;
    }

    public AgendaDTO create(AgendaDTO dto) {
        if (dto.getStatus() == null) {
            dto.setStatus(EnumAgendaSessionStatus.CREATED);
        }

        CreateAgenda createAgenda = new CreateAgenda(repository);

        return createAgenda.execute(dto);
    }

    public List<AgendaDTO> list() {
        ListAgendas listAgendas = new ListAgendas(repository);

        return listAgendas.execute();
    }

    public AgendaResultDTO countVotes(String agendaId) {
        if (!this.isSessionFinished(agendaId)) {
            throw new AgendaSessionNotFinishedException();
        }

        CountAgendaVotes countAgendaVotes = new CountAgendaVotes(repository);

        return countAgendaVotes.execute(agendaId);
    }

    private boolean isSessionFinished(String agendaId) {
        AgendaDTO agenda = this.repository.findById(agendaId)
                .orElseThrow(AgendaNotFoundException::new);

        return agenda.getStatus() == EnumAgendaSessionStatus.CLOSED;
    }

}
