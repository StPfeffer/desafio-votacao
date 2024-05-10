package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.VoteBO;

public interface IVoteRepository {

    VoteBO persist(VoteBO bo);

}
