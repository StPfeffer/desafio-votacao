package com.dbserver.desafiovotacao.core.enums;

/**
 * EnumAgendaStatus represents the possible statuses of an agenda.
 */
public enum EnumAgendaStatus {

    /**
     * Indicates that the agenda has been approved.
     *
     * @see EnumAgendaStatus#REJECTED
     * @see EnumAgendaStatus#TIED
     */
    APPROVED,

    /**
     * Indicates that the agenda has been rejected.
     *
     * @see EnumAgendaStatus#APPROVED
     * @see EnumAgendaStatus#TIED
     */
    REJECTED,

    /**
     * Indicates that the agenda vote resulted in a tie.
     *
     * @see EnumAgendaStatus#APPROVED
     * @see EnumAgendaStatus#REJECTED
     */
    TIED
}
