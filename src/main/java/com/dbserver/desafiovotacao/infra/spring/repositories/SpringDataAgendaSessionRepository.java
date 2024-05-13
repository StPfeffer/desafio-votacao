package com.dbserver.desafiovotacao.infra.spring.repositories;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataAgendaSessionRepository extends JpaRepository<PostgresAgendaSession, UUID> {

    /**
     * Retrieves a list of all open agenda sessions.
     *
     * @return A list of {@link PostgresAgendaSession} entities where the 'closedAt' field is
     * {@code null}, indicating open sessions.
     */
    @Query("SELECT e FROM PostgresAgendaSession e WHERE e.closedAt IS NULL")
    List<PostgresAgendaSession> listAllOpenSessions();

}
