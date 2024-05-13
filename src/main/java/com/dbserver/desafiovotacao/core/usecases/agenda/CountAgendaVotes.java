package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaResultMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;

public class CountAgendaVotes {

    private final IAgendaRepository repository;

    public CountAgendaVotes(IAgendaRepository repository) {
        this.repository = repository;
    }

    public AgendaResultDTO execute(String agendaId) {
        AgendaResultBO entity = this.repository.countVotes(agendaId);

        return AgendaResultMapper.toDTO(entity);
    }

}
