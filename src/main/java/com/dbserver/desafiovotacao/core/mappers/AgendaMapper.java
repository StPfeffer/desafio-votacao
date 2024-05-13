package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;

public class AgendaMapper {

    /**
     * Maps an {@link AgendaBO} object to an {@link AgendaDTO} object.
     *
     * @param bo The {@link AgendaBO} object to be mapped.
     * @return The mapped {@link AgendaDTO} object.
     */
    public static AgendaDTO toDTO(AgendaBO bo) {
        AgendaDTO dto = new AgendaDTO();

        dto.setId(bo.getId());
        dto.setTitle(bo.getTitle());
        dto.setDescription(bo.getDescription());
        dto.setStatus(bo.getStatus());

        return dto;
    }

    /**
     * Maps an {@link AgendaDTO} object to an {@link AgendaBO} object.
     *
     * @param dto The {@link AgendaDTO} object to be mapped.
     * @return The mapped {@link AgendaBO} object.
     */
    public static AgendaBO toBO(AgendaDTO dto) {
        AgendaBO bo = new AgendaBO();

        bo.setId(dto.getId());
        bo.setTitle(dto.getTitle());
        bo.setDescription(dto.getDescription());
        bo.setStatus(dto.getStatus());

        return bo;
    }

}
