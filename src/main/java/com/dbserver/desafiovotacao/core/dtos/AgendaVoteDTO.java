package com.dbserver.desafiovotacao.core.dtos;

import com.dbserver.desafiovotacao.core.enums.EnumAgendaStatus;

public class AgendaVoteDTO {

    private String agendaId;

    private Integer totalVotes;

    private Integer votesInFavor;

    private Integer votesAgains;

    private EnumAgendaStatus status;

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Integer getVotesInFavor() {
        return votesInFavor;
    }

    public void setVotesInFavor(Integer votesInFavor) {
        this.votesInFavor = votesInFavor;
    }

    public Integer getVotesAgains() {
        return votesAgains;
    }

    public void setVotesAgains(Integer votesAgains) {
        this.votesAgains = votesAgains;
    }

    public EnumAgendaStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAgendaStatus status) {
        this.status = status;
    }

}
