package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;

public class AgendaResultMapper {

    public static AgendaResultDTO toDTO(AgendaResultBO bo) {
        AgendaResultDTO dto = new AgendaResultDTO();

        dto.setAgendaId(bo.getAgendaId());
        dto.setTotalVotes(bo.getTotalVotes());
        dto.setVotesInFavor(bo.getVotesInFavor());
        dto.setVotesAgainst(bo.getVotesAgainst());
        dto.setStatus(bo.getStatus());

        return dto;
    }

    public static AgendaResultBO toBO(AgendaResultDTO dto) {
        AgendaResultBO bo = new AgendaResultBO();

        bo.setAgendaId(dto.getAgendaId());
        bo.setTotalVotes(dto.getTotalVotes());
        bo.setVotesInFavor(dto.getVotesInFavor());
        bo.setVotesAgainst(dto.getVotesAgainst());
        bo.setStatus(dto.getStatus());

        return bo;
    }

}
