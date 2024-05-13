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

/**
 * PostgresAgendaSessionRepository is a service class responsible for interacting with the PostgreSQL database
 * to perform operations related to agenda sessions.
 */
@Service
public class PostgresAgendaSessionRepository implements IAgendaSessionRepository {

    private final SpringDataAgendaSessionRepository repository;

    private final SpringDataAgendaRepository agendaRepository;

    /**
     * Constructs a new {@link PostgresAgendaSessionRepository} with the specified dependencies.
     *
     * @param repository       The {@link SpringDataAgendaSessionRepository} used to interact with agenda session entities.
     * @param agendaRepository The {@link SpringDataAgendaRepository} used to interact with agenda entities.
     */
    public PostgresAgendaSessionRepository(SpringDataAgendaSessionRepository repository, SpringDataAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    /**
     * Persists an agenda session into the database.
     *
     * @param bo       The {@link AgendaOpenSessionRequestBO} representing the agenda session to be persisted.
     * @param agendaBO The {@link AgendaBO} representing the associated agenda.
     * @return The persisted {@link AgendaOpenSessionResponseBO} entity.
     */
    @Override
    public AgendaOpenSessionResponseBO persist(AgendaOpenSessionRequestBO bo, AgendaBO agendaBO) {
        PostgresAgenda agenda = PostgresAgendaMapper.toEntity(agendaBO);

        PostgresAgendaSession entity = PostgresAgendaSessionMapper.toEntity(bo);
        entity.setAgenda(agenda);

        entity = this.repository.save(entity);
        this.agendaRepository.save(agenda);

        return PostgresAgendaSessionMapper.toDomainResponse(entity);
    }

    /**
     * Updates an existing agenda session in the database.
     *
     * @param entity The {@link PostgresAgendaSession} entity to be updated.
     */
    public void update(PostgresAgendaSession entity) {
        this.repository.save(entity);
    }

    /**
     * Retrieves a list of all open agenda sessions from the database.
     *
     * @return A list of {@link PostgresAgendaSession} entities representing open sessions.
     */
    public List<PostgresAgendaSession> listAllOpen() {
        return repository.listAllOpenSessions();
    }

}
