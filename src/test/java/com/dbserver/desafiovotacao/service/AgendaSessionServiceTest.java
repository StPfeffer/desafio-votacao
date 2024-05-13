package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AgendaSessionServiceTest {

    @Mock
    private PostgresAgendaSessionRepository sessionRepository;

    @Mock
    private PostgresAgendaRepository agendaRepository;

    @InjectMocks
    private AgendaSessionService agendaSessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_ValidSession() {
        // Arrange
        String agendaId = "d9b82a9b-d9f4-4b48-b60b-f3ad59729463";
        AgendaOpenSessionRequestDTO dto = new AgendaOpenSessionRequestDTO();
        dto.setMinutes(6);

        PostgresAgenda agenda = new PostgresAgenda();
        agenda.setId(UUID.fromString(agendaId));

        when(agendaRepository.findEntityById(agendaId)).thenReturn(Optional.of(agenda));

        PostgresAgendaSession session = new PostgresAgendaSession();
        session.setId(UUID.fromString(agendaId));
        session.setAgenda(agenda);
        session.setOpenedAt(LocalDateTime.now().minusMinutes(5)); // Session opened 5 minutes ago

        when(sessionRepository.persist(any(), any())).thenReturn(new AgendaOpenSessionResponseBO());

        // Act
        AgendaOpenSessionResponseDTO responseDTO = agendaSessionService.create(agendaId, dto);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(agendaId, session.getAgenda().getId().toString());
        assertTrue(session.getOpenedAt().isBefore(LocalDateTime.now()));
        assertTrue(session.getOpenedAt().plusMinutes(dto.getMinutes()).isAfter(LocalDateTime.now()));
        verify(sessionRepository).persist(any(), any());
    }

    @Test
    void testCreate_AgendaNotFound() {
        // Arrange
        String agendaId = "d9b82a9b-d9f4-4b48-b60b-f3ad59729463";
        AgendaOpenSessionRequestDTO dto = new AgendaOpenSessionRequestDTO();
        dto.setMinutes(5);

        when(agendaRepository.findEntityById(agendaId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AgendaNotFoundException.class, () -> agendaSessionService.create(agendaId, dto));
    }

}
