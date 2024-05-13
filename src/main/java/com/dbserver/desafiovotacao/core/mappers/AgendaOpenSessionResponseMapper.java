package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;

public class AgendaOpenSessionResponseMapper {

    public static AgendaOpenSessionResponseDTO toDTO(AgendaOpenSessionResponseBO bo) {
        AgendaOpenSessionResponseDTO dto = new AgendaOpenSessionResponseDTO();

        dto.setSessionId(bo.getSessionId());
        dto.setAgendaId(bo.getAgendaId());
        dto.setOpenedUntil(bo.getOpenedUntil());

        return dto;
    }

    public static AgendaOpenSessionResponseBO toBO(AgendaOpenSessionResponseDTO dto) {
        AgendaOpenSessionResponseBO bo = new AgendaOpenSessionResponseBO();

        bo.setSessionId(dto.getSessionId());
        bo.setAgendaId(dto.getAgendaId());
        bo.setOpenedUntil(dto.getOpenedUntil());

        return bo;
    }

}
