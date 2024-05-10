package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;

public class CreateAgenda {

    private final IAgendaRepository repository;

    public CreateAgenda(IAgendaRepository repository) {
        this.repository = repository;
    }

    public AgendaDTO execute(AgendaDTO dto) {
        AgendaBO entity = this.repository.persist(AgendaMapper.toBO(dto));

        return AgendaMapper.toDTO(entity);
    }

}
