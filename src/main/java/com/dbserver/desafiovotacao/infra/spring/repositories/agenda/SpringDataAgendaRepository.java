package com.dbserver.desafiovotacao.infra.spring.repositories.agenda;

import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataAgendaRepository extends JpaRepository<PostgresAgenda, UUID> {
}
