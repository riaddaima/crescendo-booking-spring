package com.crescendo.booking.crescendobookingspring.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
    private String name;
    private String startTime;
    private String endTime;
    private String date;
    private int capacity;
    private int minAge;
    private int maxAge;
    private String venue;
    private String description;
    private String thumbnail;
    private String type;

    public Event(String name, String startTime, String endTime, String date, int capacity, int minAge, int maxAge,
                 String venue, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.capacity = capacity;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.venue = venue;
        this.description = description;
    }

}
