package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaOpenSessionRequestMapperTest {

    @Test
    public void testToDTO() {
        // Arrange
        AgendaOpenSessionRequestBO bo = new AgendaOpenSessionRequestBO();
        bo.setMinutes(10);

        // Act
        AgendaOpenSessionRequestDTO dto = AgendaOpenSessionRequestMapper.toDTO(bo);

        // Assert
        assertNotNull(dto);
        assertEquals(10, dto.getMinutes());
    }

    @Test
    public void testToDTO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaOpenSessionRequestMapper.toDTO(null));
    }

    @Test
    public void testToBO() {
        // Arrange
        AgendaOpenSessionRequestDTO dto = new AgendaOpenSessionRequestDTO();
        dto.setMinutes(10);

        // Act
        AgendaOpenSessionRequestBO bo = AgendaOpenSessionRequestMapper.toBO(dto);

        // Assert
        assertNotNull(bo);
        assertEquals(10, bo.getMinutes());
    }

    @Test
    public void testToBO_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> AgendaOpenSessionRequestMapper.toBO(null));
    }

}
