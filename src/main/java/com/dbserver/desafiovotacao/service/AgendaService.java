package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaDTO;
import com.dbserver.desafiovotacao.core.usecases.agenda.CreateAgenda;
import com.dbserver.desafiovotacao.infra.postgres.repositories.agenda.PostgresAgendaRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

    private final PostgresAgendaRepository repository;

    public AgendaService(PostgresAgendaRepository repository) {
        this.repository = repository;
    }

    public AgendaDTO create(AgendaDTO dto) {
        CreateAgenda createAgenda = new CreateAgenda(repository);

        return createAgenda.execute(dto);
    }

}
