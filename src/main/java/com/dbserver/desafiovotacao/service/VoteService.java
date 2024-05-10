package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionAlreadyFinishedException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionNotOpenException;
import com.dbserver.desafiovotacao.core.exceptions.AlreadyVotedException;
import com.dbserver.desafiovotacao.core.usecases.vote.CreateVote;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresVoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final PostgresVoteRepository repository;

    private final PostgresAgendaRepository agendaRepository;

    public VoteService(PostgresVoteRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    public VoteDTO create(VoteDTO dto) {
        this.canVote(dto);

        CreateVote createVote = new CreateVote(repository);

        return createVote.execute(dto);
    }

    private void canVote(VoteDTO dto) {
        AgendaDTO agenda = agendaRepository.findById(dto.getAgendaId())
                .orElseThrow(AgendaNotFoundException::new);

        if (agenda.getStatus() == EnumAgendaSessionStatus.CLOSED) {
            throw new AgendaSessionAlreadyFinishedException();
        }

        if (agenda.getStatus() != EnumAgendaSessionStatus.OPEN) {
            throw new AgendaSessionNotOpenException();
        }

        if (repository.alreadyExistsByAgendaIdAndAssociateId(dto.getAgendaId(), dto.getAssociateId())) {
            throw new AlreadyVotedException();
        }
    }

}
