package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaResultDTO;
import com.dbserver.desafiovotacao.service.AgendaService;
import com.dbserver.desafiovotacao.service.AgendaSessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AgendaController {

    private final AgendaService service;

    private final AgendaSessionService sessionService;

    /**
     * Constructs a new {@link AgendaController} with the specified service instances.
     *
     * @param service        The {@link AgendaService} used to perform agenda-related operations.
     * @param sessionService The {@link AgendaSessionService} used to perform agenda session-related operations.
     */
    public AgendaController(AgendaService service, AgendaSessionService sessionService) {
        this.service = service;
        this.sessionService = sessionService;
    }

    /**
     * Handles HTTP GET requests for listing all agendas.
     *
     * @return {@link ResponseEntity} with a list of {@link AgendaDTO} and HTTP status OK.
     */
    @GetMapping("/v1/agendas")
    public ResponseEntity<List<AgendaDTO>> list() {
        List<AgendaDTO> agendas = this.service.list();

        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests for retrieving the result of a specific agenda.
     *
     * @param agendaId The ID of the agenda.
     * @return {@link ResponseEntity} with the {@link AgendaResultDTO} and HTTP status OK.
     */
    @GetMapping("/v1/agendas/{agendaId}/result")
    public ResponseEntity<AgendaResultDTO> getResult(@PathVariable String agendaId) {
        AgendaResultDTO agendaResult = this.service.countVotes(agendaId);

        return new ResponseEntity<>(agendaResult, HttpStatus.OK);
    }

    /**
     * Handles HTTP POST requests for creating a new agenda.
     *
     * @param dto The {@link AgendaDTO} representing the agenda to be created.
     * @return {@link ResponseEntity} with the created {@link AgendaDTO} and HTTP status OK.
     */
    @PostMapping("/v1/agendas")
    public ResponseEntity<AgendaDTO> create(@Valid @RequestBody AgendaDTO dto) {
        AgendaDTO createdAgenda = this.service.create(dto);

        return new ResponseEntity<>(createdAgenda, HttpStatus.OK);
    }

    /**
     * Handles HTTP POST requests for opening a session for an agenda.
     *
     * @param dto The {@link AgendaOpenSessionRequestDTO} representing the session to be opened.
     * @return {@link ResponseEntity} with the {@link AgendaOpenSessionResponseDTO} and HTTP status OK.
     */
    @PostMapping("/v1/agendas/{agendaId}/open")
    public ResponseEntity<AgendaOpenSessionResponseDTO> open(@PathVariable String agendaId, @Valid @RequestBody(required = false) AgendaOpenSessionRequestDTO dto) {
        AgendaOpenSessionResponseDTO agendaResult = this.sessionService.create(agendaId, dto);

        return new ResponseEntity<>(agendaResult, HttpStatus.OK);
    }

}
