package com.dbserver.desafiovotacao.core.mappers;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.entities.VoteBO;

public class VoteMapper {

    public static VoteDTO toDTO(VoteBO bo) {
        VoteDTO dto = new VoteDTO();

        dto.setId(bo.getId());
        dto.setAgendaId(bo.getAgendaId());
        dto.setAssociateId(bo.getAssociateId());
        dto.setVoteType(bo.getVoteType());

        return dto;
    }

    public static VoteBO toBO(VoteDTO dto) {
        VoteBO bo = new VoteBO();

        bo.setId(dto.getId());
        bo.setAgendaId(dto.getAgendaId());
        bo.setAssociateId(dto.getAssociateId());
        bo.setVoteType(dto.getVoteType());

        return bo;
    }

}
