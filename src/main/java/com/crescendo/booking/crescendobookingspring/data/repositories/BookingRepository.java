package com.crescendo.booking.crescendobookingspring.data.repositories;

import com.crescendo.booking.crescendobookingspring.data.entities.Attend;
import com.crescendo.booking.crescendobookingspring.data.entities.Booking;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends BaseRepository<Booking> {

    @Query("SELECT CASE WHEN (SELECT COUNT(*) FROM Booking WHERE event.id = :event AND isWaitList = FALSE) >= (SELECT capacity FROM Event WHERE id = :event) THEN TRUE ELSE FALSE END AS canBook")
    public boolean isWaitList(long event);
}
