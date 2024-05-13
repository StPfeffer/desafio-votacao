package com.dbserver.desafiovotacao.core.usecases.vote;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.core.mappers.VoteMapper;
import com.dbserver.desafiovotacao.core.repository.IVoteRepository;

public class CreateVote {

    private final IVoteRepository repository;

    /**
     * Constructs a {@link CreateVote} object with the specified vote repository.
     *
     * @param repository The repository for managing vote entities.
     */
    public CreateVote(IVoteRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case to create a new vote.
     *
     * @param dto The {@link VoteDTO} object representing the vote to be created.
     * @return The created {@link VoteDTO} object.
     */
    public VoteDTO execute(VoteDTO dto) {
        VoteBO entity = this.repository.persist(VoteMapper.toBO(dto));

        return VoteMapper.toDTO(entity);
    }

}
