package com.dbserver.desafiovotacao.core.entities;

import java.time.LocalDateTime;

public class AgendaOpenSessionResponseBO {

    private String sessionId;

    private String agendaId;

    private LocalDateTime openedUntil;

    public AgendaOpenSessionResponseBO() {
    }

    public AgendaOpenSessionResponseBO(String agendaId, LocalDateTime openedUntil) {
        this.agendaId = agendaId;
        this.openedUntil = openedUntil;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public LocalDateTime getOpenedUntil() {
        return openedUntil;
    }

    public void setOpenedUntil(LocalDateTime openedUntil) {
        this.openedUntil = openedUntil;
    }

}
