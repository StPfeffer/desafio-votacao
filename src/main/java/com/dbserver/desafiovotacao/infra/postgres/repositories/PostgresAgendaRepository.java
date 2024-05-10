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

@Service
public class PostgresAgendaRepository implements IAgendaRepository {

    private final SpringDataAgendaRepository repository;

    public PostgresAgendaRepository(SpringDataAgendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AgendaBO> list() {
        return this.repository.findAll().stream().map(PostgresAgendaMapper::toDomain).toList();
    }

    @Override
    public AgendaBO persist(AgendaBO bo) {
        PostgresAgenda entity = this.repository.save(PostgresAgendaMapper.toEntity(bo));

        return PostgresAgendaMapper.toDomain(entity);
    }

    public void update(PostgresAgenda entity) {
        this.repository.save(entity);
    }

    @Override
    public AgendaResultBO countVotes(String agendaId) {
        Long inFavorVotes = this.repository.countInFavorVotesByAgendaId(UUID.fromString(agendaId));
        Long againstVotes = this.repository.countAgainstVotesByAgendaId(UUID.fromString(agendaId));
        Long totalVotes = inFavorVotes + againstVotes;

        AgendaResultBO agendaVote = new AgendaResultBO(agendaId, totalVotes, inFavorVotes, againstVotes);

        agendaVote.resolveAgendaStatus();

        return agendaVote;
    }

    public Optional<AgendaDTO> findById(String id) {
        return this.repository.findById(UUID.fromString(id))
                .map(entity -> AgendaMapper.toDTO(PostgresAgendaMapper.toDomain(entity)));
    }

    public Optional<PostgresAgenda> findEntityById(String id) {
        return this.repository.findById(UUID.fromString(id));
    }

}
