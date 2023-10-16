package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import com.crescendo.booking.crescendobookingspring.enums.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
@Table(name = "\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseEntity {

    @NotNull
    @Email
    protected String email;

    protected String password;

    @NotNull
    protected String role;

    @OneToMany
    protected List<Dependent> dependents;

    @OneToOne
    protected Profile profile;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Booking> bookings;

    protected User () {}

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role.getValue();
    }
}