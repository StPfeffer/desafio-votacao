package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;

public class AgendaResultMapper {

    /**
     * Maps an {@link AgendaResultBO} object to an {@link AgendaResultDTO} object.
     *
     * @param bo The {@link AgendaResultBO} object to be mapped.
     * @return The mapped {@link AgendaResultDTO} object.
     */
    public static AgendaResultDTO toDTO(AgendaResultBO bo) {
        AgendaResultDTO dto = new AgendaResultDTO();

        dto.setAgendaId(bo.getAgendaId());
        dto.setTotalVotes(bo.getTotalVotes());
        dto.setVotesInFavor(bo.getVotesInFavor());
        dto.setVotesAgainst(bo.getVotesAgainst());
        dto.setStatus(bo.getStatus());

        return dto;
    }

    /**
     * Maps an {@link AgendaResultDTO} object to an {@link AgendaResultBO} object.
     * Note: This method is currently unused and is provided for potential future use or extension.
     *
     * @param dto The {@link AgendaResultDTO} object to be mapped.
     * @return The mapped {@link AgendaResultBO} object.
     */
    @SuppressWarnings("unused")
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
