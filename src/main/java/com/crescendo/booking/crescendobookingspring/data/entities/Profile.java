package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Setter
public class Profile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    protected User user;

    @NotNull
    protected String firstName;

    @NotNull
    protected String lastName;

    @NotNull
    protected String phoneNumber;

    @NotNull
    protected Boolean isSubbed;
}
