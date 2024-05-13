package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionAlreadyFinishedException;
import com.dbserver.desafiovotacao.core.exceptions.AgendaSessionNotOpenException;
import com.dbserver.desafiovotacao.core.exceptions.AlreadyVotedException;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaSessionRepository;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresVoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class VoteServiceTest {

    @Mock
    private PostgresVoteRepository voteRepository;

    @Mock
    private PostgresAgendaRepository agendaRepository;

    @InjectMocks
    private VoteService voteService;

    public VoteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate_AgendaNotFound() {
        // Arrange
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");
        when(agendaRepository.findById("d9b82a9b-d9f4-4b48-b60b-f3ad59729463")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AgendaNotFoundException.class, () -> voteService.create(voteDTO));
        verify(voteRepository, never()).persist(any());
    }

    @Test
    public void testCreate_AgendaAlreadyFinished() {
        // Arrange
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");

        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(UUID.randomUUID().toString());
        agendaDTO.setTitle("Test Agenda 1");
        agendaDTO.setDescription("Test Agenda 1 description");
        agendaDTO.setStatus(EnumAgendaSessionStatus.CLOSED);

        when(agendaRepository.findById("d9b82a9b-d9f4-4b48-b60b-f3ad59729463")).thenReturn(Optional.of(agendaDTO));

        // Act & Assert
        assertThrows(AgendaSessionAlreadyFinishedException.class, () -> voteService.create(voteDTO));
        verify(voteRepository, never()).persist(any());
    }

    @Test
    public void testCreate_AgendaNotOpen() {
        // Arrange
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");

        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(UUID.randomUUID().toString());
        agendaDTO.setTitle("Test Agenda 2");
        agendaDTO.setDescription("Test Agenda 2 description");
        agendaDTO.setStatus(EnumAgendaSessionStatus.CREATED);

        when(agendaRepository.findById("d9b82a9b-d9f4-4b48-b60b-f3ad59729463")).thenReturn(Optional.of(agendaDTO));

        // Act & Assert
        assertThrows(AgendaSessionNotOpenException.class, () -> voteService.create(voteDTO));
        verify(voteRepository, never()).persist(any());
    }

    @Test
    public void testCreate_AlreadyVoted() {
        // Arrange
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");
        voteDTO.setAssociateId("d9b82a9b-d9f4-4b48-b60b-f3ad59729464");

        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setId(UUID.randomUUID().toString());
        agendaDTO.setTitle("Test Agenda 3");
        agendaDTO.setDescription("Test Agenda 3 description");
        agendaDTO.setStatus(EnumAgendaSessionStatus.OPEN);

        when(agendaRepository.findById("d9b82a9b-d9f4-4b48-b60b-f3ad59729463")).thenReturn(Optional.of(agendaDTO));
        when(voteRepository.alreadyExistsByAgendaIdAndAssociateId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463", "d9b82a9b-d9f4-4b48-b60b-f3ad59729464")).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyVotedException.class, () -> voteService.create(voteDTO));
        verify(voteRepository, never()).persist(any());
    }

}
