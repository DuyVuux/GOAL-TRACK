package org.example.goaltrack.model.highlight;

import jakarta.persistence.*;
import lombok.Data;
import org.example.goaltrack.model.football.Match;
import org.example.goaltrack.model.user.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "highlight")
public class Highlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private User uploader;

    @Column(name = "video_url")
    private String videoUrl;

    private String description;

    @Column(name = "scored_minute")
    private Integer scoredMinute;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    private Integer duration;
    private String resolution;

    @Column(name = "view_count")
    private Integer viewCount = 0;
}
