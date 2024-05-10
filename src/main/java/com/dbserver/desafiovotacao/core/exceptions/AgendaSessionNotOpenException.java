package com.dbserver.desafiovotacao.core.exceptions;

public class AgendaSessionNotOpenException extends AbstractException {

    public AgendaSessionNotOpenException() {
        super("The session of the agenda associated with the provided ID has not started", 400);
    }

}
