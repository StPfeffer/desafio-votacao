package com.dbserver.desafiovotacao.infra.postgres.mappers;

import com.dbserver.desafiovotacao.core.entities.VoteBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.enums.EnumVoteType;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresVote;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class PostgresVoteMapperTest {

    @Test
    public void testToDomain_NullPostgresAgenda() {
        // Arrange
        PostgresVote entity = new PostgresVote();
        entity.setId(UUID.randomUUID());
        entity.setAgenda(new PostgresAgenda());
        entity.setAssociateId(UUID.randomUUID());
        entity.setVoteType(EnumVoteType.YES);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresVoteMapper.toDomain(entity));
    }

    @Test
    public void testToDomainWithPostgresAgenda() {
        // Arrange
        PostgresVote entity = new PostgresVote();
        entity.setId(UUID.randomUUID());
        entity.setAssociateId(UUID.randomUUID());
        entity.setVoteType(EnumVoteType.YES);

        PostgresAgenda agenda = new PostgresAgenda();
        agenda.setId(UUID.randomUUID());
        agenda.setTitle("Sample Agenda");
        agenda.setDescription("This is a sample agenda");
        agenda.setStatus(EnumAgendaSessionStatus.CREATED);

        entity.setAgenda(agenda);

        // Act
        VoteBO domain = PostgresVoteMapper.toDomain(entity);

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId().toString(), domain.getId());
        assertEquals(entity.getAgenda().getId().toString(), domain.getAgendaId());
        assertEquals(entity.getAssociateId().toString(), domain.getAssociateId());
        assertEquals(entity.getVoteType(), domain.getVoteType());
    }

    @Test
    public void testToDomain_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresVoteMapper.toDomain(null));
    }

    @Test
    public void testToEntity() {
        // Arrange
        VoteBO domain = new VoteBO();
        domain.setAssociateId(UUID.randomUUID().toString());
        domain.setVoteType(EnumVoteType.NO);

        // Act
        PostgresVote entity = PostgresVoteMapper.toEntity(domain);

        // Assert
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(UUID.fromString(domain.getAssociateId()), entity.getAssociateId());
        assertEquals(domain.getVoteType(), entity.getVoteType());
    }

    @Test
    public void testToEntity_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> PostgresVoteMapper.toEntity(null));
    }

}