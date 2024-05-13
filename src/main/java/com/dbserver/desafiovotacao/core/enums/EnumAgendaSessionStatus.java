package com.dbserver.desafiovotacao.core.enums;

/**
 * EnumAgendaSessionStatus represents the possible statuses of an agenda session.
 */
public enum EnumAgendaSessionStatus {

    /**
     * Indicates that the agenda session has been created but not yet opened.
     * @see EnumAgendaSessionStatus#OPEN
     * @see EnumAgendaSessionStatus#CLOSED
     */
    CREATED,

    /**
     * Indicates that the agenda session is currently open and accepting votes.
     * @see EnumAgendaSessionStatus#CREATED
     * @see EnumAgendaSessionStatus#CLOSED
     */
    OPEN,

    /**
     * Indicates that the agenda session has been closed and no longer accepting votes.
     * @see EnumAgendaSessionStatus#OPEN
     */
    CLOSED
}
