package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Profile extends BaseEntity {

    @OneToOne
    @NotNull
    protected User user;

    @NotNull
    protected String firstName;

    @NotNull
    protected String lastName;

    @NotNull
    protected String phoneNumber;

    @NotNull
    protected boolean isSubbed;
}
