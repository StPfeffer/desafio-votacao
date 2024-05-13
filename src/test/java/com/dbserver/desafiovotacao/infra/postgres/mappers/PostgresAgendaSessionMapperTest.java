package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PostgresAgendaSessionMapperTest {

    @Test
    public void testToDomainResponse() {
        // Arrange
        PostgresAgendaSession entity = new PostgresAgendaSession();
        entity.setId(UUID.randomUUID());
        entity.setMinutes(30);
        entity.setOpenedAt(LocalDateTime.now());

        PostgresAgenda agenda = new PostgresAgenda();
        agenda.setId(UUID.randomUUID());
        agenda.setTitle("Sample Agenda");
        agenda.setDescription("This is a sample agenda");
        agenda.setStatus(EnumAgendaSessionStatus.CREATED);

        entity.setAgenda(agenda);

        // Act
        AgendaOpenSessionResponseBO domain = PostgresAgendaSessionMapper.toDomainResponse(entity);

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId().toString(), domain.getSessionId());
        assertEquals(entity.getOpenedAt().plusMinutes(entity.getMinutes()), domain.getOpenedUntil());
    }

    @Test
    public void testToDomainResponse_NullPostgresAgenda() {
        // Arrange
        PostgresAgendaSession entity = new PostgresAgendaSession();
        entity.setId(UUID.randomUUID());
        entity.setMinutes(30);
        entity.setOpenedAt(LocalDateTime.now());

        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresAgendaSessionMapper.toDomainResponse(null));
    }

    @Test
    public void testToDomainResponse_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresAgendaSessionMapper.toDomainResponse(null));
    }

    @Test
    public void testToEntity() {
        // Arrange
        AgendaOpenSessionRequestBO domain = new AgendaOpenSessionRequestBO();
        domain.setMinutes(30);

        // Act
        PostgresAgendaSession entity = PostgresAgendaSessionMapper.toEntity(domain);

        // Assert
        assertNotNull(entity);
        assertEquals(30, entity.getMinutes());
    }

    @Test
    public void testToEntity_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresAgendaSessionMapper.toEntity(null));
    }

}