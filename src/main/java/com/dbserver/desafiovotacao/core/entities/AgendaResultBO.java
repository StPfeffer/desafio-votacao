package com.dbserver.desafiovotacao.core.entities;

import com.dbserver.desafiovotacao.core.enums.EnumAgendaStatus;

public class AgendaResultBO {

    private String agendaId;

    private Long totalVotes;

    private Long votesInFavor;

    private Long votesAgainst;

    private EnumAgendaStatus status;

    public AgendaResultBO() {
    }

    public AgendaResultBO(String agendaId, Long totalVotes, Long votesInFavor, Long votesAgainst) {
        this.agendaId = agendaId;
        this.totalVotes = totalVotes;
        this.votesInFavor = votesInFavor;
        this.votesAgainst = votesAgainst;
    }

    public void resolveAgendaStatus() {
        if (votesInFavor > votesAgainst) {
            this.status = EnumAgendaStatus.APPROVED;
        } else if (votesInFavor < votesAgainst) {
            this.status = EnumAgendaStatus.REJECTED;
        } else {
            this.status = EnumAgendaStatus.TIED;
        }
    }

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
