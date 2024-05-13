package com.dbserver.desafiovotacao.core.usecases.agenda;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;

public class CreateAgenda {

    private final IAgendaRepository repository;

    /**
     * Constructs a {@link CreateAgenda} object with the specified agenda repository.
     *
     * @param repository The repository for managing agenda entities.
     */
    public CreateAgenda(IAgendaRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to create a new agenda based on the provided data.
     *
     * @param dto The {@link AgendaDTO} containing the details of the agenda to be created.
     * @return The {@link AgendaDTO} representing the newly created agenda.
     */
    public AgendaDTO execute(AgendaDTO dto) {
        AgendaBO entity = this.repository.persist(AgendaMapper.toBO(dto));

        return AgendaMapper.toDTO(entity);
    }

}
