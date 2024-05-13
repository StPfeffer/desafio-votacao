package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;

import java.util.List;

public class ListAgendas {

    private final IAgendaRepository repository;

    /**
     * Constructs a {@link ListAgendas} object with the specified agenda repository.
     *
     * @param repository The repository for managing agenda entities.
     */
    public ListAgendas(IAgendaRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to retrieve a list of agendas.
     *
     * @return A list of {@link AgendaDTO} representing the retrieved agendas.
     */
    public List<AgendaDTO> execute() {
        List<AgendaBO> entities = this.repository.list();

        return entities.stream().map(AgendaMapper::toDTO).toList();
    }

}
