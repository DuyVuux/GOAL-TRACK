package org.example.goaltrack.model.notification;

import jakarta.persistence.*;
import lombok.Data;
import org.example.goaltrack.model.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_notification")
@IdClass(UserNotificationId.class)
public class UserNotification {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "notification_id", insertable = false, updatable = false)
    private Notification notification;
}

@Data
class UserNotificationId implements Serializable {
    private Integer userId;
    private Integer notificationId;
}
