package com.crescendo.booking.crescendobookingspring.data.entities;

import com.crescendo.booking.crescendobookingspring.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Dependent extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    protected User user;

    @NotNull
    protected String firstName;

    @NotNull
    protected String lastName;

    @NotNull
    protected Date dob;

    @NotNull
    protected String gender;
}
