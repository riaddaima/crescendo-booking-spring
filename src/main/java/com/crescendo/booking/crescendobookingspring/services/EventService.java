package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Event;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

import static com.crescendo.booking.crescendobookingspring.format.DateHelper.convertTimestampToLocalTime;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public boolean createEvent(com.crescendo.booking.crescendobookingspring.data.dtos.Event dto) {
        LocalTime startTime = convertTimestampToLocalTime(dto.getStartTime());
        LocalTime endTime = convertTimestampToLocalTime(dto.getEndTime());
        Date date = new Date(Long.parseLong(dto.getDate()));
        Event event = new Event(dto.getName(), startTime,
                endTime, date, dto.getCapacity(), dto.getMinAge(), dto.getMaxAge(), dto.getVenue(), dto.getDescription());
        eventRepository.save(event);
        return true;
    }
}
