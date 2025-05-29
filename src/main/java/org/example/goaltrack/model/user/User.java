package org.example.goaltrack.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.goaltrack.common.Gender;
import org.example.goaltrack.common.UserStatus;
import org.example.goaltrack.common.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "user", schema = "public") // Sử dụng name = "public.user" truy vấn sai !!
public class User implements UserDetails, Serializable {
    @Id // Đánh dấu là khóa chính
    @GeneratedValue() // Sinh giá trị tự động
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "gender", length = 255)
    @Enumerated(EnumType.STRING) // Lưu enum dưới dạng String
    @JdbcTypeCode(SqlTypes.NAMED_ENUM) // Ánh xạ enum với kiểu ENUM của DB
    private Gender gender;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 255) // Đảm bảo username là duy nhất "unique = true"
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type", length = 255)
    private UserType type;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", length = 255)
    private UserStatus status;

    @Column(name = "create_at", length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at", length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateAt;

    @Column(name = "is_active", length = 255)
    private Boolean isActive = true;

    @Column(name = "last_login", length = 255)
    private Date lastLogin;

    @Column(name = "secret_code", length = 255)
    private String secretCode;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Phân quyền
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.ACTIVE.equals(status);
    }
}
