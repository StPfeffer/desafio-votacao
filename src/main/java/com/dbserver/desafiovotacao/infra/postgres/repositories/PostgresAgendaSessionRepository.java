package com.dbserver.desafiovotacao.infra.postgres.repositories;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.core.repository.IAgendaSessionRepository;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaSessionMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;
import com.dbserver.desafiovotacao.infra.spring.repositories.SpringDataAgendaRepository;
import com.dbserver.desafiovotacao.infra.spring.repositories.SpringDataAgendaSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresAgendaSessionRepository implements IAgendaSessionRepository {

    private final SpringDataAgendaSessionRepository repository;

    private final SpringDataAgendaRepository agendaRepository;

    public PostgresAgendaSessionRepository(SpringDataAgendaSessionRepository repository, SpringDataAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    @Override
    public AgendaOpenSessionResponseBO persist(AgendaOpenSessionRequestBO bo, AgendaBO agendaBO) {
        PostgresAgenda agenda = PostgresAgendaMapper.toEntity(agendaBO);

        PostgresAgendaSession entity = PostgresAgendaSessionMapper.toEntity(bo);
        entity.setAgenda(agenda);

        entity = this.repository.save(entity);
        this.agendaRepository.save(agenda);

        return PostgresAgendaSessionMapper.toDomainResponse(entity);
    }

    public void update(PostgresAgendaSession entity) {
        this.repository.save(entity);
    }

    public List<PostgresAgendaSession> listAllOpen() {
        return repository.listAllOpenSessions();
    }

}
