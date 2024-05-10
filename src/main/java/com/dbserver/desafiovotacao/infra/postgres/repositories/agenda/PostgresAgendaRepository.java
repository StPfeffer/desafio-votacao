package com.dbserver.desafiovotacao.infra.postgres.repositories.agenda;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.spring.repositories.agenda.SpringDataAgendaRepository;
import org.springframework.stereotype.Service;

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

}
