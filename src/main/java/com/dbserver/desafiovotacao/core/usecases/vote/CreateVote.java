package com.dbserver.desafiovotacao.core.usecases.vote;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.core.mappers.VoteMapper;
import com.dbserver.desafiovotacao.core.repository.IVoteRepository;

public class CreateVote {

    private final IVoteRepository repository;

    public CreateVote(IVoteRepository repository) {
        this.repository = repository;
    }

    public VoteDTO execute(VoteDTO dto) {
        VoteBO entity = this.repository.persist(VoteMapper.toBO(dto));

        return VoteMapper.toDTO(entity);
    }

}
