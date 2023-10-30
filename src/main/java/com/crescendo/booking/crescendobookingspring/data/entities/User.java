package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import com.crescendo.booking.crescendobookingspring.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
public class User {

    @Id
    @GeneratedValue
    protected long id;

    @Column(name="created_at", updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name="modified_at")
    @LastModifiedDate
    private long modifiedDate;

    @NotNull
    @Email
    protected String email;

    protected String password;

    @NotNull
    protected String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected List<Dependent> dependents;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected Profile profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    protected List<Booking> bookings;

    protected User () {}

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role.getValue();
    }
}