package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;

public class AgendaOpenSessionResponseMapper {

    /**
     * Maps an {@link AgendaOpenSessionResponseBO} object to an {@link AgendaOpenSessionResponseDTO} object.
     *
     * @param bo The {@link AgendaOpenSessionResponseBO} object to be mapped.
     * @return The mapped {@link AgendaOpenSessionResponseDTO} object.
     */
    public static AgendaOpenSessionResponseDTO toDTO(AgendaOpenSessionResponseBO bo) {
        AgendaOpenSessionResponseDTO dto = new AgendaOpenSessionResponseDTO();

        dto.setSessionId(bo.getSessionId());
        dto.setAgendaId(bo.getAgendaId());
        dto.setOpenedUntil(bo.getOpenedUntil());

        return dto;
    }

    /**
     * Maps an {@link AgendaOpenSessionResponseDTO} object to an {@link AgendaOpenSessionResponseBO} object.
     *
     * @param dto The {@link AgendaOpenSessionResponseDTO} object to be mapped.
     * @return The mapped {@link AgendaOpenSessionResponseBO} object.
     */
    public static AgendaOpenSessionResponseBO toBO(AgendaOpenSessionResponseDTO dto) {
        AgendaOpenSessionResponseBO bo = new AgendaOpenSessionResponseBO();

        bo.setSessionId(dto.getSessionId());
        bo.setAgendaId(dto.getAgendaId());
        bo.setOpenedUntil(dto.getOpenedUntil());

        return bo;
    }

}
