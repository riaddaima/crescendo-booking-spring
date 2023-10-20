package com.crescendo.booking.crescendobookingspring.data.dtos;

import com.crescendo.booking.crescendobookingspring.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Profile implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean isSubbed;
}
