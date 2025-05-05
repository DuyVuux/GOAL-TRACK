package org.example.goaltrack.model.user;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_has_role")
@IdClass(UserHasRoleId.class)
public class UserHasRole {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}

@Data
class UserHasRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;
}
