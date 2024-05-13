package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        AgendaBO bo = new AgendaBO();
        bo.setId("1");
        bo.setTitle("Test Agenda");
        bo.setDescription("Test Description");
        bo.setStatus(EnumAgendaSessionStatus.OPEN);

        // Act
        AgendaDTO dto = AgendaMapper.toDTO(bo);

        // Assert
        assertNotNull(dto);
        assertEquals("1", dto.getId());
        assertEquals("Test Agenda", dto.getTitle());
        assertEquals("Test Description", dto.getDescription());
        assertEquals(EnumAgendaSessionStatus.OPEN, dto.getStatus());
    }

    @Test
    public void testToDTO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaMapper.toDTO(null));
    }

    @Test
    public void testToBO() {
        // Arrange
        AgendaDTO dto = new AgendaDTO();
        dto.setId("1");
        dto.setTitle("Test Agenda");
        dto.setDescription("Test Description");
        dto.setStatus(EnumAgendaSessionStatus.OPEN);

        // Act
        AgendaBO bo = AgendaMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertEquals("1", bo.getId());
        assertEquals("Test Agenda", bo.getTitle());
        assertEquals("Test Description", bo.getDescription());
        assertEquals(EnumAgendaSessionStatus.OPEN, bo.getStatus());
    }

    @Test
    public void testToBO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaMapper.toBO(null));
    }

    @Test
    public void testToBO_NullFields() {
        // Arrange
        AgendaDTO dto = new AgendaDTO();

        // Act
        AgendaBO bo = AgendaMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertNull(bo.getId());
        assertNull(bo.getTitle());
        assertNull(bo.getDescription());
        assertNull(bo.getStatus());
    }

}
