package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.service.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AgendaController {

    private final AgendaService service;

    public AgendaController(AgendaService service) {
        this.service = service;
    }

    @PostMapping("/v1/agendas")
    public ResponseEntity<AgendaDTO> create(@RequestBody AgendaDTO dto) {
        AgendaDTO createdAgenda = this.service.create(dto);

        return new ResponseEntity<>(createdAgenda, HttpStatus.OK);
    }

}
