package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;

import java.util.UUID;

public class PostgresVoteMapper {

    /**
     * Maps a {@link PostgresVote} entity to a {@link VoteBO} domain object.
     *
     * @param entity The {@link PostgresVote} entity to be mapped.
     * @return The corresponding {@link VoteBO} domain object.
     * @see PostgresVoteMapper#toEntity(VoteBO)
     */
    public static VoteBO toDomain(PostgresVote entity) {
        VoteBO domain = new VoteBO();

        domain.setId(entity.getId().toString());
        domain.setAgendaId(entity.getAgenda().getId().toString());
        domain.setAssociateId(entity.getAssociateId().toString());
        domain.setVoteType(entity.getVoteType());

        return domain;
    }

    /**
     * Maps a {@link VoteBO} domain object to a {@link PostgresVote} entity.
     *
     * @param domain The {@link VoteBO} domain object to be mapped.
     * @return The corresponding {@link PostgresVote} entity.
     * @see PostgresVoteMapper#toDomain(PostgresVote)
     */
    public static PostgresVote toEntity(VoteBO domain) {
        PostgresVote entity = new PostgresVote();

        entity.setId(domain.getId() == null ? UUID.randomUUID() : UUID.fromString(domain.getId()));
        entity.setAssociateId(UUID.fromString(domain.getAssociateId()));
        entity.setVoteType(domain.getVoteType());

        return entity;
    }

}
