package com.crescendo.booking.crescendobookingspring.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Dependent implements Serializable {

    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;

}
