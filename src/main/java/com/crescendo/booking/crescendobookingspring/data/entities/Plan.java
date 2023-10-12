package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Plan extends BaseEntity {

    @NotNull
    protected String title;

    @NotNull
    protected String type;

    @NotNull
    protected float price;

    @NotNull
    protected int numKids;
}
