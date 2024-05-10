package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;

public class AgendaMapper {

    public static AgendaDTO toDTO(AgendaBO bo) {
        AgendaDTO dto = new AgendaDTO();

        dto.setId(bo.getId());
        dto.setTitle(bo.getTitle());
        dto.setDescription(bo.getDescription());

        return dto;
    }

    public static AgendaBO toBO(AgendaDTO dto) {
        AgendaBO bo = new AgendaBO();

        bo.setId(dto.getId());
        bo.setTitle(dto.getTitle());
        bo.setDescription(dto.getDescription());

        return bo;
    }

}
