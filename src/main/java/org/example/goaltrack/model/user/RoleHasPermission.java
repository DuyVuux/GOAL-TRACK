package org.example.goaltrack.model.user;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "role_has_permission")
@IdClass(RoleHasPermissionId.class)
public class RoleHasPermission {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
    private Permission permission;
}

@Data
class RoleHasPermissionId implements Serializable {
    private Integer roleId;
    private Integer permissionId;
}
