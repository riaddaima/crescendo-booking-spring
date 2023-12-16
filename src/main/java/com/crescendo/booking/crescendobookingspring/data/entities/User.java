package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import com.crescendo.booking.crescendobookingspring.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
public class User {

    @Id
    protected long id;

    @NotNull
    @Email
    protected String email;

    @NotNull
    protected String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected List<Dependent> dependents;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected Profile profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    protected List<Booking> bookings;

    @ManyToMany(mappedBy = "instructors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<Event> events;

    protected User () {}

    public User(long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}