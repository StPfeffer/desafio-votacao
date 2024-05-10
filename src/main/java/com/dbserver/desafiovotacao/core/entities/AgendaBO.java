package com.dbserver.desafiovotacao.core.entities;

import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;

public class AgendaBO {

    private String id;

    private String title;

    private String description;

    private EnumAgendaSessionStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumAgendaSessionStatus getStatus() {
        return status;
    }

    public void setStatus(EnumAgendaSessionStatus status) {
        this.status = status;
    }

}
