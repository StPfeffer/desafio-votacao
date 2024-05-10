package com.dbserver.desafiovotacao.core.exceptions;

public class AgendaNotFoundException extends AbstractException {

    public AgendaNotFoundException() {
        super("There is no agenda with the provided ID", 400);
    }

}
