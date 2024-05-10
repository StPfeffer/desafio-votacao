package com.dbserver.desafiovotacao.core.exceptions;

public class AgendaSessionAlreadyFinishedException extends AbstractException {

    public AgendaSessionAlreadyFinishedException() {
        super("The session of the agenda associated with the provided ID is already finished", 400);
    }

}
