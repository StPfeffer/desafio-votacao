package com.dbserver.desafiovotacao.infra.spring.repositories;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataVoteRepository extends JpaRepository<PostgresVote, UUID> {

    /**
     * Retrieves the first vote associated with the given agenda and associate ID.
     *
     * @param agenda      The {@link PostgresAgenda} entity associated with the vote.
     * @param associateId The {@link UUID} of the associate who voted.
     * @return The first {@link PostgresVote} entity found for the given agenda and associate ID,
     * or {@code null} if none is found.
     */
    PostgresVote findFirstByAgendaAndAssociateId(PostgresAgenda agenda, UUID associateId);

}
