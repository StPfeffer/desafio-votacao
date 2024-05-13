package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.VoteBO;

public interface IVoteRepository {

    /**
     * Persists a vote.
     *
     * @param bo The {@link VoteBO} object representing the vote to be persisted.
     * @return The persisted {@link VoteBO} object.
     */
    VoteBO persist(VoteBO bo);

}
