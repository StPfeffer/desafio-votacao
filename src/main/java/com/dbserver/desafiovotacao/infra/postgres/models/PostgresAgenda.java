package com.dbserver.desafiovotacao.infra.postgres.models;

import com.dbserver.desafiovotacao.core.enums.EnumAgendaStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "agenda")
public class PostgresAgenda {

    @Id
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumAgendaStatus status;

}
