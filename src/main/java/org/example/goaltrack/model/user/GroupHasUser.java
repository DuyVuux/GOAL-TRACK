package org.example.goaltrack.model.user;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "group_has_user")
@IdClass(GroupHasUserId.class)
public class GroupHasUser {
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}

@Data
class GroupHasUserId implements Serializable {
    private Integer groupId;
    private Integer userId;
}
