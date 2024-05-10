package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;

public interface IAgendaRepository {

    AgendaBO persist(AgendaBO bo);

}
