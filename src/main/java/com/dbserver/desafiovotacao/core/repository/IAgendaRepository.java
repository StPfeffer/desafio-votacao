package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaResultBO;

import java.util.List;

public interface IAgendaRepository {

    List<AgendaBO> list();

    AgendaBO persist(AgendaBO bo);

    AgendaResultBO countVotes(String agendaId);

}
