package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaOpenSessionResponseMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        AgendaOpenSessionResponseBO bo = new AgendaOpenSessionResponseBO();
        bo.setSessionId("session-123");
        bo.setAgendaId("agenda-456");
        bo.setOpenedUntil(LocalDateTime.now());

        // Act
        AgendaOpenSessionResponseDTO dto = AgendaOpenSessionResponseMapper.toDTO(bo);

        // Assert
        assertNotNull(dto);
        assertEquals("session-123", dto.getSessionId());
        assertEquals("agenda-456", dto.getAgendaId());
        assertEquals(bo.getOpenedUntil(), dto.getOpenedUntil());
    }

    @Test
    public void testToDTO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaOpenSessionResponseMapper.toDTO(null));
    }

    @Test
    public void testToBO() {
        // Arrange
        AgendaOpenSessionResponseDTO dto = new AgendaOpenSessionResponseDTO();
        dto.setSessionId("session-123");
        dto.setAgendaId("agenda-456");
        dto.setOpenedUntil(LocalDateTime.now());

        // Act
        AgendaOpenSessionResponseBO bo = AgendaOpenSessionResponseMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertEquals("session-123", bo.getSessionId());
        assertEquals("agenda-456", bo.getAgendaId());
        assertEquals(dto.getOpenedUntil(), bo.getOpenedUntil());
    }

    @Test
    public void testToBO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaOpenSessionResponseMapper.toBO(null));
    }

}