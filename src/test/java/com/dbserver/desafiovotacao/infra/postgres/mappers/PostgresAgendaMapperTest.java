package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PostgresAgendaMapperTest {

    @Test
    public void testToDomain() {
        // Arrange
        PostgresAgenda entity = new PostgresAgenda();
        entity.setId(UUID.randomUUID());
        entity.setTitle("Sample Agenda");
        entity.setDescription("This is a sample agenda");
        entity.setStatus(EnumAgendaSessionStatus.CREATED);

        // Act
        AgendaBO domain = PostgresAgendaMapper.toDomain(entity);

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId().toString(), domain.getId());
        assertEquals("Sample Agenda", domain.getTitle());
        assertEquals("This is a sample agenda", domain.getDescription());
        assertEquals(EnumAgendaSessionStatus.CREATED, domain.getStatus());
    }

    @Test
    public void testToDomain_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresAgendaMapper.toDomain(null));
    }

    @Test
    public void testToEntity() {
        // Arrange
        AgendaBO domain = new AgendaBO();
        domain.setId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");
        domain.setTitle("Sample Agenda");
        domain.setDescription("This is a sample agenda");
        domain.setStatus(EnumAgendaSessionStatus.CREATED);

        // Act
        PostgresAgenda entity = PostgresAgendaMapper.toEntity(domain);

        // Assert
        assertNotNull(entity);
        assertEquals(UUID.fromString("d9b82a9b-d9f4-4b48-b60b-f3ad59729463"), entity.getId());
        assertEquals("Sample Agenda", entity.getTitle());
        assertEquals("This is a sample agenda", entity.getDescription());
        assertEquals(EnumAgendaSessionStatus.CREATED, entity.getStatus());
    }

    @Test
    public void testToEntity_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresAgendaMapper.toEntity(null));
    }

}