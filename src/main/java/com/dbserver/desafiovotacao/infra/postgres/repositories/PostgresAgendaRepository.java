package com.dbserver.desafiovotacao.infra.postgres.repositories;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.spring.repositories.SpringDataAgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * PostgresAgendaRepository is a service class responsible for interacting with the PostgreSQL database
 * to perform operations related to agenda entities.
 */
@Service
public class PostgresAgendaRepository implements IAgendaRepository {

    private final SpringDataAgendaRepository repository;

    /**
     * Constructs a new {@link PostgresAgendaRepository} with the specified {@link SpringDataAgendaRepository}
     * dependency.
     *
     * @param repository The {@link SpringDataAgendaRepository} used to interact with agenda entities.
     */
    public PostgresAgendaRepository(SpringDataAgendaRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a list of all agenda entities.
     *
     * @return A list of {@link AgendaBO} representing all agenda entities.
     */
    @Override
    public List<AgendaBO> list() {
        return this.repository.findAll().stream().map(PostgresAgendaMapper::toDomain).toList();
    }

    /**
     * Persists an agenda entity into the database.
     *
     * @param bo The {@link AgendaBO} representing the agenda to be persisted.
     * @return The persisted {@link AgendaBO} entity.
     */
    @Override
    public AgendaBO persist(AgendaBO bo) {
        PostgresAgenda entity = this.repository.save(PostgresAgendaMapper.toEntity(bo));

        return PostgresAgendaMapper.toDomain(entity);
    }

    /**
     * Updates an existing agenda entity in the database.
     *
     * @param entity The {@link PostgresAgenda} entity to be updated.
     */
    public void update(PostgresAgenda entity) {
        this.repository.save(entity);
    }

    /**
     * Counts the number of votes in favor of and against a specific agenda.
     *
     * @param agendaId The {@link UUID} of the agenda.
     * @return An {@link AgendaResultBO} object containing the vote counts and agenda status.
     */
    @Override
    public AgendaResultBO countVotes(String agendaId) {
        Long inFavorVotes = this.repository.countInFavorVotesByAgendaId(UUID.fromString(agendaId));
        Long againstVotes = this.repository.countAgainstVotesByAgendaId(UUID.fromString(agendaId));
        Long totalVotes = inFavorVotes + againstVotes;

        AgendaResultBO agendaVote = new AgendaResultBO(agendaId, totalVotes, inFavorVotes, againstVotes);

        agendaVote.resolveAgendaStatus();

        return agendaVote;
    }

    /**
     * Finds an agenda DTO by its ID.
     *
     * @param id The {@link UUID} of the agenda.
     * @return An {@link Optional} containing the {@link AgendaDTO} if found, otherwise empty.
     */
    public Optional<AgendaDTO> findById(String id) {
        return this.repository.findById(UUID.fromString(id))
                .map(entity -> AgendaMapper.toDTO(PostgresAgendaMapper.toDomain(entity)));
    }

    /**
     * Finds an agenda entity by its ID.
     *
     * @param id The {@link UUID} of the agenda.
     * @return An {@link Optional} containing the {@link PostgresAgenda} if found, otherwise empty.
     */
    public Optional<PostgresAgenda> findEntityById(String id) {
        return this.repository.findById(UUID.fromString(id));
    }

}
