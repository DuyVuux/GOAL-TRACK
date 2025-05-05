package org.example.goaltrack.model.football;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "standing")
@IdClass(StandingId.class)
public class Standing {
    @Id
    @Column(name = "tournament_id")
    private Integer tournamentId;

    @Id
    @Column(name = "team_id")
    private Integer teamId;

    private Integer position;
    private Integer points;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "tournament_id", insertable = false, updatable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;
}

@Data
class StandingId implements Serializable {
    private Integer tournamentId;
    private Integer teamId;
}
