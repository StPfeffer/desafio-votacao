package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;

import java.util.List;

public class ListAgendas {

    private final IAgendaRepository repository;

    public ListAgendas(IAgendaRepository repository) {
        this.repository = repository;
    }

    public List<AgendaDTO> execute() {
        List<AgendaBO> entities = this.repository.list();

        return entities.stream().map(AgendaMapper::toDTO).toList();
    }

}
