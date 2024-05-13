package com.dbserver.desafiovotacao.infra.spring.repositories;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataAgendaRepository extends JpaRepository<PostgresAgenda, UUID> {

    // A denormalization of the database will ensure better performance. Since we can add these two
    // properties to PostgresAgenda: 'votesInFavor' and 'votesAgainst'.

    /**
     * Counts the number of votes in favor of a specific agenda.
     *
     * @param agendaId The {@link UUID} of the agenda.
     * @return The count of votes in favor of the specified agenda.
     */
    @Query("SELECT COUNT(v) FROM PostgresVote v WHERE v.agenda.id = :agendaId AND v.voteType = 'YES'")
    Long countInFavorVotesByAgendaId(UUID agendaId);

    /**
     * Counts the number of votes against a specific agenda.
     *
     * @param agendaId The {@link UUID} of the agenda.
     * @return The count of votes against the specified agenda.
     */
    @Query("SELECT COUNT(v) FROM PostgresVote v WHERE v.agenda.id = :agendaId AND v.voteType = 'NO'")
    Long countAgainstVotesByAgendaId(UUID agendaId);

}
