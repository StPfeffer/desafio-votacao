package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class VoteController {

    private final VoteService service;

    /**
     * Constructs a new {@link VoteController} with the specified service instance.
     *
     * @param service The {@link VoteService} used to perform vote-related operations.
     */
    public VoteController(VoteService service) {
        this.service = service;
    }

    /**
     * Handles HTTP POST requests for creating a new vote.
     *
     * @param dto The {@link VoteDTO} representing the vote to be created.
     * @return {@link ResponseEntity} with the created {@link VoteDTO} and HTTP status OK.
     */
    @Operation(summary = "Vote for an agenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully voted for the agenda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VoteDTO.class))})
    })
    @PostMapping("/v1/votes")
    public ResponseEntity<VoteDTO> create(@Valid @RequestBody VoteDTO dto) {
        VoteDTO createdVote = service.create(dto);

        return ResponseEntity.ok(createdVote);
    }

}
