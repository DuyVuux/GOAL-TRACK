package org.example.goaltrack.model.notification;

import jakarta.persistence.*;
import lombok.Data;
import org.example.goaltrack.model.football.Match;
import org.example.goaltrack.model.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_follow_match")
@IdClass(UserFollowMatchId.class)
public class UserFollowMatch {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "match_id")
    private Integer matchId;

    @Column(name = "follow_at")
    private LocalDateTime followAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    private Match match;
}

@Data
class UserFollowMatchId implements Serializable {
    private Integer userId;
    private Integer matchId;
}
