package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;

public interface IAgendaSessionRepository {

    /**
     * Persists an agenda session.
     *
     * @param bo       The {@link AgendaOpenSessionRequestBO} object representing the session request.
     * @param agendaBO The {@link AgendaBO} object associated with the session.
     * @return The response containing information about the opened session.
     */
    AgendaOpenSessionResponseBO persist(AgendaOpenSessionRequestBO bo, AgendaBO agendaBO);

}
