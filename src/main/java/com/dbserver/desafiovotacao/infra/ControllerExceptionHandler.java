package com.dbserver.desafiovotacao.infra;

import com.dbserver.desafiovotacao.core.dtos.ExceptionDTO;
import com.dbserver.desafiovotacao.core.exceptions.AbstractException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles general exceptions that are not specifically caught by other handlers.
     *
     * @param exception The exception to be handled.
     * @return {@link ResponseEntity} containing the appropriate error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> threatGeneralExceptions(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");

        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    /**
     * Handles {@link DataIntegrityViolationException} which typically occurs due to data integrity constraints.
     *
     * @param exception The exception to be handled.
     * @return {@link ResponseEntity} containing the bad request response.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> threatDataIntegrityViolationExceptions(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "400");

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    /**
     * Handles {@link AbstractException}, which is a custom exception type for the application.
     *
     * @param exception The exception to be handled.
     * @return {@link ResponseEntity} containing the appropriate error response based on the exception's status code.
     */
    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ExceptionDTO> threatAgendaNotFoundException(AbstractException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), String.valueOf(exception.getStatusCode()));

        return switch (exception.getStatusCode()) {
            case 400 -> ResponseEntity.badRequest().body(exceptionDTO);
            case 404 -> ResponseEntity.notFound().build();
            default -> ResponseEntity.internalServerError().body(exceptionDTO);
        };
    }

}
