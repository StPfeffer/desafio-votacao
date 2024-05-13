package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;

import java.util.UUID;

public class PostgresAgendaSessionMapper {

    public static PostgresAgendaSession toEntity(AgendaOpenSessionRequestBO domain) {
        PostgresAgendaSession entity = new PostgresAgendaSession();

        entity.setId(UUID.randomUUID());
        entity.setMinutes(domain.getMinutes());

        return entity;
    }

    public static AgendaOpenSessionResponseBO toDomainResponse(PostgresAgendaSession entity) {
        AgendaOpenSessionResponseBO domain = new AgendaOpenSessionResponseBO();

        domain.setSessionId(entity.getId().toString());
        domain.setAgendaId(entity.getAgenda().getId().toString());
        domain.setOpenedUntil(entity.getOpenedAt().plusMinutes(entity.getMinutes()));

        return domain;
    }

}
