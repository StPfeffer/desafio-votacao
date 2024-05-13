package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;

import java.util.UUID;

public class PostgresAgendaSessionMapper {

    /**
     * Maps an {@link AgendaOpenSessionRequestBO} domain object to a {@link PostgresAgendaSession} entity.
     *
     * @param domain The {@link AgendaOpenSessionRequestBO} domain object to be mapped.
     * @return The corresponding {@link PostgresAgendaSession} entity.
     * @see PostgresAgendaSessionMapper#toDomainResponse(PostgresAgendaSession)
     */
    public static PostgresAgendaSession toEntity(AgendaOpenSessionRequestBO domain) {
        PostgresAgendaSession entity = new PostgresAgendaSession();

        entity.setId(UUID.randomUUID());
        entity.setMinutes(domain.getMinutes());

        return entity;
    }

    /**
     * Maps a {@link PostgresAgendaSession} entity to an {@link AgendaOpenSessionResponseBO} domain object.
     *
     * @param entity The {@link PostgresAgendaSession} entity to be mapped.
     * @return The corresponding {@link AgendaOpenSessionResponseBO} domain object.
     * @see PostgresAgendaSessionMapper#toEntity(AgendaOpenSessionRequestBO)
     */
    public static AgendaOpenSessionResponseBO toDomainResponse(PostgresAgendaSession entity) {
        AgendaOpenSessionResponseBO domain = new AgendaOpenSessionResponseBO();

        domain.setSessionId(entity.getId().toString());
        domain.setAgendaId(entity.getAgenda().getId().toString());
        domain.setOpenedUntil(entity.getOpenedAt().plusMinutes(entity.getMinutes()));

        return domain;
    }

}
