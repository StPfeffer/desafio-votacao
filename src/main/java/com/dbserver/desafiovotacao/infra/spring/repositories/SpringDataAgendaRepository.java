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
    @Query("SELECT COUNT(v) FROM PostgresVote v WHERE v.agenda.id = :agendaId AND v.voteType = 'YES'")
    Long countInFavorVotesByAgendaId(UUID agendaId);

    @Query("SELECT COUNT(v) FROM PostgresVote v WHERE v.agenda.id = :agendaId AND v.voteType = 'NO'")
    Long countAgainstVotesByAgendaId(UUID agendaId);

}
