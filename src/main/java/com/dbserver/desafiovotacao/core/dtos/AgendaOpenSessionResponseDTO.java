package com.dbserver.desafiovotacao.core.dtos;

import java.time.LocalDateTime;

public class AgendaOpenSessionResponseDTO {

    private String sessionId;

    private String agendaId;

    private LocalDateTime openedUntil;

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
