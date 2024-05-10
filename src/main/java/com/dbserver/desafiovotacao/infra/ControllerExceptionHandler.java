package com.dbserver.desafiovotacao.infra;

import com.dbserver.desafiovotacao.core.dtos.ExceptionDTO;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.exceptions.AlreadyVotedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> threatGeneralExceptions(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");

        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> threatDataIntegrityViolationExceptions(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threatAgendaNotFoundException(AgendaNotFoundException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), String.valueOf(exception.getStatusCode()));

        return switch (exception.getStatusCode()) {
            case 400 -> ResponseEntity.badRequest().body(exceptionDTO);
            case 404 -> ResponseEntity.notFound().build();
            default -> ResponseEntity.internalServerError().body(exceptionDTO);
        };
    }

    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<ExceptionDTO> threatAlreadyVotedException(AlreadyVotedException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), String.valueOf(exception.getStatusCode()));

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

}
