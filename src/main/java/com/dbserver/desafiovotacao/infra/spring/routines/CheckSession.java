package com.dbserver.desafiovotacao.infra.spring.routines;

import com.dbserver.desafiovotacao.service.AgendaSessionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckSession {

    private final AgendaSessionService sessionService;

    public CheckSession(AgendaSessionService sessionService) {
        this.sessionService = sessionService;
    }

    // Each 10 seconds
    @Scheduled(fixedDelay = 10000)
    public void closeSessions() {
        this.sessionService.closeSessions();
    }
}
