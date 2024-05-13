package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaResultMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountAgendaVotes {

    private final IAgendaRepository repository;

    private static final Logger log = LoggerFactory.getLogger(CountAgendaVotes.class);

    /**
     * Constructs a {@link CountAgendaVotes} object with the specified agenda repository.
     *
     * @param repository The repository for managing agenda entities.
     */
    public CountAgendaVotes(IAgendaRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to count votes for the specified agenda.
     *
     * @param agendaId The ID of the agenda for which votes are to be counted.
     * @return The result of vote counting for the agenda.
     */
    public AgendaResultDTO execute(String agendaId) {
        AgendaResultBO entity = this.repository.countVotes(agendaId);

        log.info("Counting the votes for agenda: {}", agendaId);

        return AgendaResultMapper.toDTO(entity);
    }

}
