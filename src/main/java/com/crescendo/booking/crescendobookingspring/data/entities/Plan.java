package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
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
