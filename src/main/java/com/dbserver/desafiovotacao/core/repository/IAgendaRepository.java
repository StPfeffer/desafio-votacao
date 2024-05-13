package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;

import java.util.List;

public interface IAgendaRepository {

    /**
     * Retrieves a list of all agenda entities.
     *
     * @return A list of {@link AgendaBO} objects.
     * @see IAgendaRepository#persist(AgendaBO)
     * @see IAgendaRepository#countVotes(String)
     */
    List<AgendaBO> list();

    /**
     * Persists an {@link AgendaBO} object.
     *
     * @param bo The {@link AgendaBO} object to be persisted.
     * @return The persisted {@link AgendaBO} object.
     * @see IAgendaRepository#list()
     * @see IAgendaRepository#countVotes(String)
     */
    AgendaBO persist(AgendaBO bo);

    /**
     * Counts votes for a specific agenda.
     *
     * @param agendaId The ID of the agenda for which votes are counted.
     * @return An {@link AgendaResultBO} object containing the vote count result.
     * @see IAgendaRepository#list()
     * @see IAgendaRepository#persist(AgendaBO)
     */
    AgendaResultBO countVotes(String agendaId);

}
