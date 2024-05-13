package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionRequestDTO;
import com.dbserver.desafiovotacao.core.dtos.AgendaOpenSessionResponseDTO;
import com.dbserver.desafiovotacao.core.entities.AgendaBO;
import com.dbserver.desafiovotacao.core.enums.EnumAgendaSessionStatus;
import com.dbserver.desafiovotacao.core.exceptions.AgendaNotFoundException;
import com.dbserver.desafiovotacao.core.usecases.agenda.session.CreateAgendaSession;
import com.dbserver.desafiovotacao.infra.postgres.mappers.PostgresAgendaMapper;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgenda;
import com.dbserver.desafiovotacao.infra.postgres.models.PostgresAgendaSession;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaRepository;
import com.dbserver.desafiovotacao.infra.postgres.repositories.PostgresAgendaSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaSessionService {

    private static final Logger log = LoggerFactory.getLogger(AgendaSessionService.class);

    private final PostgresAgendaSessionRepository repository;

    private final PostgresAgendaRepository agendaRepository;

    public AgendaSessionService(PostgresAgendaSessionRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    public AgendaOpenSessionResponseDTO create(AgendaOpenSessionRequestDTO dto) {
        PostgresAgenda agenda = this.agendaRepository.findEntityById(dto.getAgendaId())
                .orElseThrow(AgendaNotFoundException::new);

        AgendaBO agendaBO = PostgresAgendaMapper.toDomain(agenda);
        agendaBO.tryToOpenAgendaSession();

        CreateAgendaSession createAgendaSession = new CreateAgendaSession(repository);

        log.info("Opening voting session for agenda: {}", agenda.getId().toString());

        return createAgendaSession.execute(dto, agendaBO);
    }

    public void closeSessions() {
        List<PostgresAgendaSession> sessions = repository.listAllOpen();

        sessions.stream().filter(this::checkIfSessionExpired).forEach(session -> {
            session.setClosedAt(LocalDateTime.now());

            log.info("Closing voting session: {}, agenda: {}", session.getId().toString(), session.getAgenda().getId().toString());

            this.repository.update(session);

            PostgresAgenda agenda = session.getAgenda();
            agenda.setStatus(EnumAgendaSessionStatus.CLOSED);

            this.agendaRepository.update(agenda);
        });
    }

    private boolean checkIfSessionExpired(PostgresAgendaSession session) {
        return LocalDateTime.now().isAfter(session.getOpenedAt().plusMinutes(session.getMinutes()));
    }

}
