package com.crescendo.booking.crescendobookingspring.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Plan implements Serializable {
    private String title;
    private String type;
    private float price;
    private int numKids;
}
