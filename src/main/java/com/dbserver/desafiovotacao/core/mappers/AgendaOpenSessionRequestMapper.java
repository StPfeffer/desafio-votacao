package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;

public class AgendaOpenSessionRequestMapper {

    /**
     * Maps an {@link AgendaOpenSessionRequestBO} object to an {@link AgendaOpenSessionRequestDTO} object.
     *
     * @param bo The {@link AgendaOpenSessionRequestBO} object to be mapped.
     * @return The mapped {@link AgendaOpenSessionRequestDTO} object.
     * @see AgendaOpenSessionRequestMapper#toBO(AgendaOpenSessionRequestDTO)
     */
    public static AgendaOpenSessionRequestDTO toDTO(AgendaOpenSessionRequestBO bo) {
        AgendaOpenSessionRequestDTO dto = new AgendaOpenSessionRequestDTO();

        dto.setAgendaId(bo.getAgendaId());
        dto.setMinutes(bo.getMinutes());

        return dto;
    }

    /**
     * Maps an {@link AgendaOpenSessionRequestDTO} object to an {@link AgendaOpenSessionRequestBO} object.
     *
     * @param dto The {@link AgendaOpenSessionRequestDTO} object to be mapped.
     * @return The mapped {@link AgendaOpenSessionRequestBO} object.
     * @see AgendaOpenSessionRequestMapper#toDTO(AgendaOpenSessionRequestBO)
     */
    public static AgendaOpenSessionRequestBO toBO(AgendaOpenSessionRequestDTO dto) {
        AgendaOpenSessionRequestBO bo = new AgendaOpenSessionRequestBO();

        bo.setAgendaId(dto.getAgendaId());
        bo.setMinutes(dto.getMinutes());

        return bo;
    }

}
