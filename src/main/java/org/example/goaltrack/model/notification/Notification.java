package org.example.goaltrack.model.notification;

import jakarta.persistence.*;
import lombok.Data;
import org.example.goaltrack.model.football.Match;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    private String title;
    private String message;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "notification_type")
    private String notificationType;
}
