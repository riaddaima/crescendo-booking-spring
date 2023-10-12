package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Attend extends BaseEntity {

    @ManyToOne
    @NotNull
    protected Booking booking;

    @ManyToOne
    @NotNull
    protected Dependent dependent;

    @NotNull
    protected boolean absent = false;
}
