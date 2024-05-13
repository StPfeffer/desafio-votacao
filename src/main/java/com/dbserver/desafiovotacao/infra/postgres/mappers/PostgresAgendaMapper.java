package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;

import java.util.UUID;

public class PostgresAgendaMapper {

    /**
     * Maps a {@link PostgresAgenda} entity to an {@link AgendaBO} domain object.
     *
     * @param entity The {@link PostgresAgenda} entity to be mapped.
     * @return The corresponding {@link AgendaBO} domain object.
     * @see PostgresAgendaMapper#toEntity(AgendaBO)
     */
    public static AgendaBO toDomain(PostgresAgenda entity) {
        AgendaBO domain = new AgendaBO();

        domain.setId(entity.getId().toString());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setStatus(entity.getStatus());

        return domain;
    }

    /**
     * Maps an {@link AgendaBO} domain object to a {@link PostgresAgenda} entity.
     *
     * @param domain The {@link AgendaBO} domain object to be mapped.
     * @return The corresponding {@link PostgresAgenda} entity.
     * @see PostgresAgendaMapper#toDomain(PostgresAgenda)
     */
    public static PostgresAgenda toEntity(AgendaBO domain) {
        PostgresAgenda entity = new PostgresAgenda();

        entity.setId(domain.getId() == null ? UUID.randomUUID() : UUID.fromString(domain.getId()));
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setStatus(domain.getStatus());

        return entity;
    }

}
