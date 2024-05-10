package com.dbserver.desafiovotacao.infra.postgres.models;

import com.dbserver.desafiovotacao.core.enums.EnumVoteType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"agenda_id", "associate_id"})
})
public class PostgresVote {

    @Id
    @Column(name = "id")
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private PostgresAgenda agenda;

    @NotNull
    @Column(name = "associate_id")
    private UUID associateId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type")
    private EnumVoteType voteType;

}
