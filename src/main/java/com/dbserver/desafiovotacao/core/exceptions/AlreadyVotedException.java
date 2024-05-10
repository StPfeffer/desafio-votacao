package com.dbserver.desafiovotacao.core.exceptions;

public class AlreadyVotedException extends AbstractException {

    public AlreadyVotedException() {
        super("This associate already voted for this agenda", 400);
    }

}
