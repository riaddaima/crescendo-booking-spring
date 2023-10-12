package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.crescendo.booking.crescendobookingspring.enums.Role;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @NotNull
    protected String email;

    protected String password;

    @NotNull
    protected Role role;

    @OneToMany
    protected List<Dependent> dependents;

    @OneToOne
    @NotNull
    protected Profile profile;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Booking> bookings;
}