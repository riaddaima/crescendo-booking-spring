package com.crescendo.booking.crescendobookingspring.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Event extends BaseEntity {

    @NotNull
    protected String name;

    @NotNull
    protected LocalTime startTime;

    @NotNull
    protected LocalTime endTime;

    @AssertTrue(message = "End time must be after start time")
    private boolean isEndTimeAfterStartTime() {
        if (startTime == null || endTime == null) {
            return true; // Let @NotNull handle null values
        }
        return endTime.isAfter(startTime);
    }

    @NotNull
    protected Date date;

    @NotNull
    protected int capacity;

    @NotNull
    protected int minAge;

    @NotNull
    protected int maxAge;

    @NotNull
    protected String venue;

    @NotNull
    protected String description;

    protected String thumbnail;

    protected String type;

    @OneToMany(fetch = FetchType.LAZY)
    protected List<Booking> bookings;

    public Event(String name, LocalTime startTime, LocalTime endTime, Date date, int capacity, int minAge, int maxAge,
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
