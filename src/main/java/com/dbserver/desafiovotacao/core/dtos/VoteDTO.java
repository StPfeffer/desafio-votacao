package com.dbserver.desafiovotacao.core.dtos;

import com.dbserver.desafiovotacao.core.enums.EnumVoteType;

public class VoteDTO {

    private String id;

    private String agendaId;

    private String associateId;

    private EnumVoteType voteType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public EnumVoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(EnumVoteType voteType) {
        this.voteType = voteType;
    }

}
