package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    @ManyToOne
    @NotNull
    protected Plan plan;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Attend> attendance;

    public Booking(User user, Event event, boolean isWaitList, Plan plan) {
        this.user = user;
        this.event = event;
        this.isWaitList = isWaitList;
        this.plan = plan;
    }
}
