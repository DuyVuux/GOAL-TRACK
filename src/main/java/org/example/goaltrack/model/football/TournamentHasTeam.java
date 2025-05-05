package org.example.goaltrack.model.football;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tournament_has_team")
@IdClass(TournamentHasTeamId.class)
public class TournamentHasTeam {
    @Id
    @Column(name = "tournament_id")
    private Integer tournamentId;

    @Id
    @Column(name = "team_id")
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "tournament_id", insertable = false, updatable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;
}

@Data
class TournamentHasTeamId implements Serializable {
    private Integer tournamentId;
    private Integer teamId;
}
