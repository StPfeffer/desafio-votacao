package com.dbserver.desafiovotacao.core.exceptions;

public class AgendaSessionAlreadyOpenedException extends AbstractException {

    public AgendaSessionAlreadyOpenedException() {
        super("The session of the agenda associated with the provided ID is already opened", 400);
    }

}
