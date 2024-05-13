package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;

public class VoteMapper {

    /**
     * Maps a {@link VoteBO} object to a {@link VoteDTO} object.
     *
     * @param bo The {@link VoteBO} object to be mapped.
     * @return The mapped {@link VoteDTO} object.
     * @see VoteMapper#toBO(VoteDTO)
     */
    public static VoteDTO toDTO(VoteBO bo) {
        VoteDTO dto = new VoteDTO();

        dto.setId(bo.getId());
        dto.setAgendaId(bo.getAgendaId());
        dto.setAssociateId(bo.getAssociateId());
        dto.setVoteType(bo.getVoteType());

        return dto;
    }

    /**
     * Maps a {@link VoteDTO} object to a {@link VoteBO} object.
     *
     * @param dto The {@link VoteDTO} object to be mapped.
     * @return The mapped {@link VoteBO} object.
     * @see VoteMapper#toDTO(VoteBO)
     */
    public static VoteBO toBO(VoteDTO dto) {
        VoteBO bo = new VoteBO();

        bo.setId(dto.getId());
        bo.setAgendaId(dto.getAgendaId());
        bo.setAssociateId(dto.getAssociateId());
        bo.setVoteType(dto.getVoteType());

        return bo;
    }

}
