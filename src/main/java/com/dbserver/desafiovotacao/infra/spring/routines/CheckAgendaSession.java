package com.dbserver.desafiovotacao.infra.spring.routines;

import com.dbserver.desafiovotacao.service.AgendaSessionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckAgendaSession {

    private final AgendaSessionService sessionService;

    /**
     * Constructs a new CheckAgendaSession instance with the provided {@link AgendaSessionService} dependency.
     *
     * @param sessionService The {@link AgendaSessionService} used to interact with agenda sessions.
     */
    public CheckAgendaSession(AgendaSessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Scheduled task to close agenda sessions at regular intervals.
     * This method is invoked periodically based on the fixed delay specified.
     * It delegates the session closing operation to the {@link AgendaSessionService}.
     */
    @Scheduled(fixedDelay = 10000)
    public void closeSessions() {
        this.sessionService.closeSessions();
    }
}
