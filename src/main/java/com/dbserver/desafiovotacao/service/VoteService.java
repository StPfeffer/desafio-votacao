package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.exceptions.AlreadyVotedException;
import com.dbserver.desafiovotacao.core.usecases.vote.CreateVote;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresVoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final PostgresVoteRepository repository;

    public VoteService(PostgresVoteRepository repository) {
        this.repository = repository;
    }

    public VoteDTO create(VoteDTO dto) {
        this.canVote(dto);

        CreateVote createVote = new CreateVote(repository);

        return createVote.execute(dto);
    }

    private void canVote(VoteDTO dto) {
        if (repository.countByAgendaIdAndAssociateId(dto.getAgendaId(), dto.getAssociateId()) != 0) {
            throw new AlreadyVotedException();
        }
    }

}
