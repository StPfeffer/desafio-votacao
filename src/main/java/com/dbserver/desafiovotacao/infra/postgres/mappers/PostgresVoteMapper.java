package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;

import java.util.UUID;

public class PostgresVoteMapper {

    public static VoteBO toDomain(PostgresVote entity) {
        VoteBO domain = new VoteBO();

        domain.setId(entity.getId().toString());
        domain.setAgendaId(entity.getAgenda().getId().toString());
        domain.setAssociateId(entity.getAssociateId().toString());
        domain.setVoteType(entity.getVoteType());

        return domain;
    }

    public static PostgresVote toEntity(VoteBO domain) {
        PostgresVote entity = new PostgresVote();

        entity.setId(domain.getId() == null ? UUID.randomUUID() : UUID.fromString(domain.getId()));
        entity.setAssociateId(UUID.fromString(domain.getAssociateId()));
        entity.setVoteType(domain.getVoteType());

        return entity;
    }

}
