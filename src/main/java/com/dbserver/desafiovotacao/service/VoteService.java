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

    /**
     * Constructs a new {@link VoteService} with the specified repositories.
     *
     * @param repository       The {@link PostgresVoteRepository} used to interact with vote entities.
     * @param agendaRepository The {@link PostgresAgendaRepository} used to interact with agenda entities.
     */
    public VoteService(PostgresVoteRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    /**
     * Creates a new vote.
     *
     * @param dto The {@link VoteDTO} representing the vote to be created.
     * @return The created {@link VoteDTO}.
     */
    public VoteDTO create(VoteDTO dto) {
        this.canVote(dto);

        CreateVote createVote = new CreateVote(repository);

        return createVote.execute(dto);
    }

    /**
     * Checks if a vote can be cast for the specified agenda.
     *
     * @param dto The {@link VoteDTO} representing the vote to be cast.
     * @throws AgendaNotFoundException               if the specified agenda is not found.
     * @throws AgendaSessionAlreadyFinishedException if the session for the agenda is already finished.
     * @throws AgendaSessionNotOpenException         if the session for the agenda is not open.
     * @throws AlreadyVotedException                 if the associate has already voted for the agenda.
     */
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
