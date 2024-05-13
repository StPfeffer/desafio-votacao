package com.dbserver.desafiovotacao.infra.postgres.repositories;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.mappers.AgendaMapper;
import com.dbserver.desafiovotacao.core.repository.IVoteRepository;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresVoteMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;
import com.dbserver.desafiovotacao.infra.spring.repositories.SpringDataVoteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * PostgresVoteRepository is a service class responsible for interacting with the PostgreSQL database
 * to perform operations related to voting entities.
 */
@Service
public class PostgresVoteRepository implements IVoteRepository {

    private final SpringDataVoteRepository repository;

    private final PostgresAgendaRepository agendaRepository;

    /**
     * Constructs a new {@link PostgresVoteRepository} with the specified dependencies.
     *
     * @param repository       The {@link SpringDataVoteRepository} used to interact with vote entities.
     * @param agendaRepository The {@link PostgresAgendaRepository} used to interact with agenda entities.
     */
    public PostgresVoteRepository(SpringDataVoteRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    /**
     * Persists a vote business object into the database.
     *
     * @param bo The {@link VoteBO} representing the vote to be persisted.
     * @return The persisted {@link VoteBO} entity.
     * @throws AgendaNotFoundException if the associated agenda is not found.
     */
    @Override
    public VoteBO persist(VoteBO bo) throws AgendaNotFoundException {
        AgendaDTO agenda = agendaRepository.findById(bo.getAgendaId())
                .orElseThrow(AgendaNotFoundException::new);

        PostgresVote entity = PostgresVoteMapper.toEntity(bo);
        entity.setAgenda(PostgresAgendaMapper.toEntity(AgendaMapper.toBO(agenda)));

        entity = this.repository.save(entity);

        return PostgresVoteMapper.toDomain(entity);
    }

    /**
     * Checks if a vote already exists for the specified agenda and associate.
     *
     * @param agendaId    The {@link UUID} of the agenda.
     * @param associateId The {@link UUID} of the associate.
     * @return True if a vote already exists for the specified agenda and associate, false otherwise.
     * @throws AgendaNotFoundException if the associated agenda is not found.
     */
    public boolean alreadyExistsByAgendaIdAndAssociateId(String agendaId, String associateId) throws AgendaNotFoundException {
        PostgresAgenda agenda = this.agendaRepository.findEntityById(agendaId)
                .orElseThrow(AgendaNotFoundException::new);

        return this.repository.findFirstByAgendaAndAssociateId(agenda, UUID.fromString(associateId)) != null;
    }

}
