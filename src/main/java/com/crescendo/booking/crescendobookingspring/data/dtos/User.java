package com.crescendo.booking.crescendobookingspring.data.dtos;

import com.crescendo.booking.crescendobookingspring.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private String email;
    private String password;
    private Role role;
}
