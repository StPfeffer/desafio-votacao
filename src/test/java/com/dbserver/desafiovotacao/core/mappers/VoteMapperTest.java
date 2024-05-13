package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.core.enums.EnumVoteType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoteMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        VoteBO bo = new VoteBO();
        bo.setId("vote-123");
        bo.setAgendaId("agenda-456");
        bo.setAssociateId("associate-789");
        bo.setVoteType(EnumVoteType.YES);

        // Act
        VoteDTO dto = VoteMapper.toDTO(bo);

        // Assert
        assertNotNull(dto);
        assertEquals("vote-123", dto.getId());
        assertEquals("agenda-456", dto.getAgendaId());
        assertEquals("associate-789", dto.getAssociateId());
        assertEquals(EnumVoteType.YES, dto.getVoteType());
    }

    @Test
    public void testToDTO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> VoteMapper.toDTO(null));
    }

    @Test
    public void testToBO() {
        // Arrange
        VoteDTO dto = new VoteDTO();
        dto.setId("vote-123");
        dto.setAgendaId("agenda-456");
        dto.setAssociateId("associate-789");
        dto.setVoteType(EnumVoteType.YES);

        // Act
        VoteBO bo = VoteMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertEquals("vote-123", bo.getId());
        assertEquals("agenda-456", bo.getAgendaId());
        assertEquals("associate-789", bo.getAssociateId());
        assertEquals(EnumVoteType.YES, bo.getVoteType());
    }

    @Test
    public void testToBO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> VoteMapper.toBO(null));
    }

}