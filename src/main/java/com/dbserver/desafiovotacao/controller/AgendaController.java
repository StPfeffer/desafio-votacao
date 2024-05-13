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

    public AgendaController(AgendaService service, AgendaSessionService sessionService) {
        this.service = service;
        this.sessionService = sessionService;
    }

    @GetMapping( "/v1/agendas")
    public ResponseEntity<List<AgendaDTO>> list() {
        List<AgendaDTO> agendas = this.service.list();

        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }

    @GetMapping("/v1/agendas/{agendaId}/result")
    public ResponseEntity<AgendaResultDTO> getResult(@PathVariable String agendaId) {
        AgendaResultDTO agendaResult = this.service.countVotes(agendaId);

        return new ResponseEntity<>(agendaResult, HttpStatus.OK);
    }

    @PostMapping("/v1/agendas")
    public ResponseEntity<AgendaDTO> create(@Valid @RequestBody AgendaDTO dto) {
        AgendaDTO createdAgenda = this.service.create(dto);

        return new ResponseEntity<>(createdAgenda, HttpStatus.OK);
    }

    @PostMapping("/v1/agendas/session/open")
    public ResponseEntity<AgendaOpenSessionResponseDTO> open(@RequestBody AgendaOpenSessionRequestDTO dto) {
        AgendaOpenSessionResponseDTO agendaResult = this.sessionService.create(dto);

        return new ResponseEntity<>(agendaResult, HttpStatus.OK);
    }

}
