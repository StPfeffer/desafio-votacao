package com.dbserver.desafiovotacao.core.dtos;

public class AgendaOpenSessionRequestDTO {

    private String agendaId;

    private Integer minutes;

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

}
