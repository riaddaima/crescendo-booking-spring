package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Booking extends BaseEntity {

    @ManyToOne
    @NotNull
    protected User user;

    @ManyToOne
    @NotNull
    protected Event event;

    @NotNull
    protected boolean hasCancelled = false;

    @NotNull
    protected boolean isWaitList;

    @NotNull
    protected float price;

    @ManyToOne
    @NotNull
    protected Plan plan;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Attend> attendance;
}
