package com.dbserver.desafiovotacao.core.repository;

import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionRequestBO;
import com.dbserver.desafiovotacao.core.entities.AgendaOpenSessionResponseBO;

public interface IAgendaSessionRepository {

    AgendaOpenSessionResponseBO persist(AgendaOpenSessionRequestBO bo, AgendaBO agendaBO);

}
