package com.dbserver.desafiovotacao.core.dtos;

import com.dbserver.desafiovotacao.core.enums.EnumAgendaStatus;

public class AgendaResultDTO {

    private String agendaId;

    private Long totalVotes;

    private Long votesInFavor;

    private Long votesAgainst;

    private EnumAgendaStatus status;

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Long getVotesInFavor() {
        return votesInFavor;
    }

    public void setVotesInFavor(Long votesInFavor) {
        this.votesInFavor = votesInFavor;
    }

    public Long getVotesAgainst() {
        return votesAgainst;
    }

    public void setVotesAgainst(Long votesAgainst) {
        this.votesAgainst = votesAgainst;
    }

    public EnumAgendaStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAgendaStatus status) {
        this.status = status;
    }

}
