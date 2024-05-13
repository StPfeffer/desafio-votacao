package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.dtos.VoteDTO;
import com.dbserver.desafiovotacao.core.enums.EnumVoteType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public VoteControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void createVote_withinAgendaSessionNotStarted() throws Exception {
        this.buildResultActionsForAgenda()
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        VoteDTO vote = new VoteDTO();
        vote.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");
        vote.setVoteType(EnumVoteType.YES);
        vote.setAssociateId(UUID.randomUUID().toString());

        this.mockMvc.perform(post("/api/v1/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(vote)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("The session of the agenda associated with the provided ID has not started"));
    }

    @Test
    void createVote_withinSameAssociatedId() throws Exception {
        this.buildResultActionsForAgenda("d9b82a9b-d9f4-4b48-b60b-f3bd79729460")
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        VoteDTO vote = new VoteDTO();
        vote.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3bd79729460");
        vote.setVoteType(EnumVoteType.YES);
        vote.setAssociateId("d9b82a9b-d9f4-4b48-b60b-f4ad59587463");

        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3bd79729460/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(vote)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(vote)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("This associate already voted for this agenda"));
    }

    @Test
    void createVote() throws Exception {
        this.buildResultActionsForAgenda("d9b82a9b-d9f4-4b48-b60b-f3ad59729460")
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        VoteDTO vote = new VoteDTO();
        vote.setAgendaId("d9b82a9b-d9f4-4b48-b60b-f3ad59729460");
        vote.setVoteType(EnumVoteType.YES);
        vote.setAssociateId(UUID.randomUUID().toString());

        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729460/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(vote)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());
    }

    private ResultActions buildResultActionsForAgenda() throws Exception {
        return this.buildResultActionsForAgenda("d9b82a9b-d9f4-4b48-b60b-f3ad59729463");
    }

    private ResultActions buildResultActionsForAgenda(String agendaId) throws Exception {
        AgendaDTO agenda = new AgendaDTO();
        agenda.setId(agendaId);
        agenda.setTitle("Agenda title");
        agenda.setDescription("Agenda description");

        return this.mockMvc.perform(post("/api/v1/agendas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agenda)));
    }

}