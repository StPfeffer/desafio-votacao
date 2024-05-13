package com.dbserver.desafiovotacao.infra.postgres.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "agenda_session")
public class PostgresAgendaSession {

    @Id
    @Column(name = "id")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private PostgresAgenda agenda;

    @NotNull
    @Column(name = "minutes")
    private Integer minutes;

    @NotNull
    @Column(name = "opened_at")
    private LocalDateTime openedAt = LocalDateTime.now();

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

}
