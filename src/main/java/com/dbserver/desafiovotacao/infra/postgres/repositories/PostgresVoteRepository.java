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

@Service
public class PostgresVoteRepository implements IVoteRepository {

    private final SpringDataVoteRepository repository;

    private final PostgresAgendaRepository agendaRepository;

    public PostgresVoteRepository(SpringDataVoteRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    @Override
    public VoteBO persist(VoteBO bo) {
        AgendaDTO agenda = agendaRepository.findById(bo.getAgendaId())
                .orElseThrow(AgendaNotFoundException::new);

        PostgresVote entity = PostgresVoteMapper.toEntity(bo);
        entity.setAgenda(PostgresAgendaMapper.toEntity(AgendaMapper.toBO(agenda)));

        entity = this.repository.save(entity);

        return PostgresVoteMapper.toDomain(entity);
    }

    public int countByAgendaIdAndAssociateId(String agendaId, String associateId) {
        PostgresAgenda agenda = this.agendaRepository.findEntityById(agendaId).orElseThrow(AgendaNotFoundException::new);

        return this.repository.countByAgendaAndAssociateId(agenda, UUID.fromString(associateId));
    }

}