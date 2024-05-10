package com.dbserver.desafiovotacao.infra.spring.repositories;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataVoteRepository extends JpaRepository<PostgresVote, UUID> {
}
