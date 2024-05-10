package com.dbserver.desafiovotacao.infra.spring.repositories;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataVoteRepository extends JpaRepository<PostgresVote, UUID> {

    PostgresVote findFirstByAgendaAndAssociateId(PostgresAgenda agenda, UUID associateId);

}
