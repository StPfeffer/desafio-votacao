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

    /**
     * Constructs a new {@link AgendaSessionService} with the specified repositories.
     *
     * @param repository       The {@link PostgresAgendaSessionRepository} used to interact with agenda session entities.
     * @param agendaRepository The {@link PostgresAgendaRepository} used to interact with agenda entities.
     */
    public AgendaSessionService(PostgresAgendaSessionRepository repository, PostgresAgendaRepository agendaRepository) {
        this.repository = repository;
        this.agendaRepository = agendaRepository;
    }

    /**
     * Creates a new session for the specified agenda.
     *
     * @param agendaId The ID of the agenda.
     * @param dto      The {@link AgendaOpenSessionRequestDTO} representing the session to be created.
     * @return The {@link AgendaOpenSessionResponseDTO} containing information about the created session.
     * @throws AgendaNotFoundException if the specified agenda is not found.
     */
    public AgendaOpenSessionResponseDTO create(String agendaId, AgendaOpenSessionRequestDTO dto) {
        PostgresAgenda agenda = this.agendaRepository.findEntityById(agendaId)
                .orElseThrow(AgendaNotFoundException::new);

        AgendaBO agendaBO = PostgresAgendaMapper.toDomain(agenda);
        agendaBO.tryToOpenAgendaSession();

        if (dto == null) {
            dto = new AgendaOpenSessionRequestDTO();
        }

        if (dto.getMinutes() == null || dto.getMinutes() < 1) {
            dto.setMinutes(1);
        }

        CreateAgendaSession createAgendaSession = new CreateAgendaSession(repository);

        log.info("Opening voting session for agenda: {}", agenda.getId().toString());

        return createAgendaSession.execute(dto, agendaBO);
    }

    /**
     * Closes expired agenda sessions.
     */
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

    /**
     * Checks if a session has expired.
     *
     * @param session The session to check.
     * @return {@code true} if the session has expired, otherwise {@code false}.
     */
    private boolean checkIfSessionExpired(PostgresAgendaSession session) {
        return LocalDateTime.now().isAfter(session.getOpenedAt().plusMinutes(session.getMinutes()));
    }

}
