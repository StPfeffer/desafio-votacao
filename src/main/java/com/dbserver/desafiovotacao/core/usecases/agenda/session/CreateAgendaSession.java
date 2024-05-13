package com.dbserver.desafiovotacao.core.usecases.agenda.session;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaOpenSessionRequestMapper;
import com.dbserver.desafiovotacao.core.mappers.AgendaOpenSessionResponseMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaSessionRepository;

public class CreateAgendaSession {

    private final IAgendaSessionRepository repository;

    public CreateAgendaSession(IAgendaSessionRepository repository) {
        this.repository = repository;
    }

    public AgendaOpenSessionResponseDTO execute(AgendaOpenSessionRequestDTO dto, AgendaBO agendaBO) {
        AgendaOpenSessionResponseBO entity = this.repository.persist(AgendaOpenSessionRequestMapper.toBO(dto), agendaBO);

        return AgendaOpenSessionResponseMapper.toDTO(entity);
    }

}
