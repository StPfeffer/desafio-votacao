package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AgendaControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public AgendaControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void listAllAgendas() throws Exception {
        this.mockMvc.perform(get("/api/v1/agendas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createAgenda() throws Exception {
        this.buildResultActionsForAgenda()
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void createAgenda_withoutTitle() throws Exception {
        AgendaDTO agenda = new AgendaDTO();
        agenda.setDescription("Agenda description");

        this.mockMvc.perform(post("/api/v1/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(agenda)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("Missing required property 'title'"));
    }

    @Test
    void openAgenda_withinInvalidAgendaId() throws Exception {
        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729449/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("There is no agenda with the provided ID"));
    }

    @Test
    void openAgenda_alreadyOpen() throws Exception {
        this.buildResultActionsForAgenda("d9b82a9b-d9f4-4b48-b60b-f3ad59729386")
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729386/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729386/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("The session of the agenda associated with the provided ID is already opened"));
    }

    @Test
    void openAgenda() throws Exception {
        this.buildResultActionsForAgenda()
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(post("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729463/open")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void getResults_withinInvalidAgendaId() throws Exception {
        this.mockMvc.perform(get("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729449/result")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("There is no agenda with the provided ID"));
    }

    @Test
    void getResults_withinSessionNotFinished() throws Exception {
        this.buildResultActionsForAgenda()
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isMap());

        this.mockMvc.perform(get("/api/v1/agendas/d9b82a9b-d9f4-4b48-b60b-f3ad59729463/result")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("message").value("The session of the agenda associated with the provided ID is not finished"));
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