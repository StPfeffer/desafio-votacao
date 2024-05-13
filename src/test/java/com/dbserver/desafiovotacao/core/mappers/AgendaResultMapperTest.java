package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaResultMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        AgendaResultBO bo = new AgendaResultBO();
        bo.setAgendaId("agenda-123");
        bo.setTotalVotes(100L);
        bo.setVotesInFavor(70L);
        bo.setVotesAgainst(30L);
        bo.setStatus(EnumAgendaStatus.APPROVED);

        // Act
        AgendaResultDTO dto = AgendaResultMapper.toDTO(bo);

        // Assert
        assertNotNull(dto);
        assertEquals("agenda-123", dto.getAgendaId());
        assertEquals(100, dto.getTotalVotes());
        assertEquals(70, dto.getVotesInFavor());
        assertEquals(30, dto.getVotesAgainst());
        assertEquals(EnumAgendaStatus.APPROVED, dto.getStatus());
    }

    @Test
    public void testToDTO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaResultMapper.toDTO(null));
    }

    @Test
    public void testToBO() {
        // Arrange
        AgendaResultDTO dto = new AgendaResultDTO();
        dto.setAgendaId("agenda-123");
        dto.setTotalVotes(100L);
        dto.setVotesInFavor(70L);
        dto.setVotesAgainst(30L);
        dto.setStatus(EnumAgendaStatus.APPROVED);

        // Act
        AgendaResultBO bo = AgendaResultMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertEquals("agenda-123", bo.getAgendaId());
        assertEquals(100, bo.getTotalVotes());
        assertEquals(70, bo.getVotesInFavor());
        assertEquals(30, bo.getVotesAgainst());
        assertEquals(EnumAgendaStatus.APPROVED, bo.getStatus());
    }

    @Test
    public void testToBO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaResultMapper.toBO(null));
    }

}