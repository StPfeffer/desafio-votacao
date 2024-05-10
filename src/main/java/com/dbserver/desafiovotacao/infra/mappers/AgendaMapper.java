package com.dbserver.desafiovotacao.infra.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.infra.models.Agenda;

import java.util.UUID;

public class AgendaMapper {

    public static AgendaDTO toDTO(Agenda entity) {
        AgendaDTO dto = new AgendaDTO();

        dto.setId(entity.getId().toString());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public static Agenda toEntity(AgendaDTO dto) {
        Agenda entity = new Agenda();

        entity.setId(dto.getId() == null ? UUID.randomUUID() : UUID.fromString(dto.getId()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
