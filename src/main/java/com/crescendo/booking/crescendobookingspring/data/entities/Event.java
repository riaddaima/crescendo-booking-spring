package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends BaseEntity {

    @NotNull
    protected String name;

    @NotNull
    protected LocalTime startTime;

    @NotNull
    protected LocalTime endTime;

    @NotNull
    protected Date date;

    @NotNull
    protected int capacity;

    @NotNull
    protected int minAge;

    @NotNull
    protected int maxAge;

    @NotNull
    protected String venue;

    @NotNull
    protected String description;

    protected String thumbnail;

    protected String type;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Booking> bookings;
}
