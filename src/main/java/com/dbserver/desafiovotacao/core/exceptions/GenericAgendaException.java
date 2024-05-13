package com.dbserver.desafiovotacao.core.exceptions;

public class GenericAgendaException extends AbstractException {

    public GenericAgendaException(String message, Integer statusCode) {
        super(message, statusCode);
    }

}
