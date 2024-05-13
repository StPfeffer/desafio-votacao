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

    /**
     * Constructs a {@link CreateAgendaSession} object with the specified agenda session repository.
     *
     * @param repository The repository for managing agenda session entities.
     */
    public CreateAgendaSession(IAgendaSessionRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to create a new session for an agenda.
     *
     * @param dto      The {@link AgendaOpenSessionRequestDTO} containing the request details for opening a session.
     * @param agendaBO The {@link AgendaBO} for which the session is being opened.
     * @return A {@link AgendaOpenSessionResponseDTO} representing the response containing the details of the newly
     * created session.
     */
    public AgendaOpenSessionResponseDTO execute(AgendaOpenSessionRequestDTO dto, AgendaBO agendaBO) {
        AgendaOpenSessionResponseBO entity = this.repository.persist(AgendaOpenSessionRequestMapper.toBO(dto), agendaBO);

        return AgendaOpenSessionResponseMapper.toDTO(entity);
    }

}
