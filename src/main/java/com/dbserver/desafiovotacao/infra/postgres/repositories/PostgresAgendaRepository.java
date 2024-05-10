package com.dbserver.desafiovotacao.infra.postgres.repositories;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.spring.repositories.SpringDataAgendaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostgresAgendaRepository implements IAgendaRepository {

    private final SpringDataAgendaRepository repository;

    public PostgresAgendaRepository(SpringDataAgendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public AgendaBO persist(AgendaBO bo) {
        PostgresAgenda entity = this.repository.save(PostgresAgendaMapper.toEntity(bo));

        return PostgresAgendaMapper.toDomain(entity);
    }

    public Optional<AgendaDTO> findById(String id) {
        return this.repository.findById(UUID.fromString(id))
                .map(entity -> AgendaMapper.toDTO(PostgresAgendaMapper.toDomain(entity)));
    }

    public Optional<PostgresAgenda> findEntityById(String id) {
        return this.repository.findById(UUID.fromString(id));
    }

}
