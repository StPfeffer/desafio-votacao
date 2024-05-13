package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;

public class AgendaOpenSessionRequestMapper {

    public static AgendaOpenSessionRequestDTO toDTO(AgendaOpenSessionRequestBO bo) {
        AgendaOpenSessionRequestDTO dto = new AgendaOpenSessionRequestDTO();

        dto.setAgendaId(bo.getAgendaId());
        dto.setMinutes(bo.getMinutes());

        return dto;
    }

    public static AgendaOpenSessionRequestBO toBO(AgendaOpenSessionRequestDTO dto) {
        AgendaOpenSessionRequestBO bo = new AgendaOpenSessionRequestBO();

        bo.setAgendaId(dto.getAgendaId());
        bo.setMinutes(dto.getMinutes());

        return bo;
    }

}
