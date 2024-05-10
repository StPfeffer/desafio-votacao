package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping("/v1/votes")
    public ResponseEntity<VoteDTO> create(@Valid @RequestBody VoteDTO dto) {
        VoteDTO createdVote = this.service.create(dto);

        return new ResponseEntity<>(createdVote, HttpStatus.OK);
    }

}
