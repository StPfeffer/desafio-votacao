package com.dbserver.desafiovotacao.core.exceptions;

public class AgendaSessionNotFinishedException extends AbstractException {

    public AgendaSessionNotFinishedException() {
        super("The session of the agenda associated with the provided ID is not finished", 400);
    }

}
