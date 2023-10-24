package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Event;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Date;

import static com.crescendo.booking.crescendobookingspring.format.dateHelper.convertTimestampToLocalTime;

@RestController
@RequestMapping("/rest/event")
public class EventRestController {

    @Autowired
    EventRepository eventRepository;

    @PostMapping
    public boolean createEvent(@RequestBody Event dto) {
        if (!validateFields(dto))
            return false;
        LocalTime startTime = convertTimestampToLocalTime(dto.getStartTime());
        LocalTime endTime = convertTimestampToLocalTime(dto.getEndTime());
        Date date = new Date(Long.parseLong(dto.getDate()));
        com.crescendo.booking.crescendobookingspring.data.entities.Event event
                = new com.crescendo.booking.crescendobookingspring.data.entities.Event(dto.getName(), startTime,
                endTime, date, dto.getCapacity(), dto.getMinAge(), dto.getMaxAge(), dto.getVenue(), dto.getDescription());
        eventRepository.save(event);
        return true;
    }

    private boolean validateFields(Event dto) {
        return (dto.getName() != null && dto.getStartTime() != null && dto.getEndTime() != null
        && Long.parseLong(dto.getEndTime()) >= Long.parseLong(dto.getStartTime()) && dto.getDate() != null
                && dto.getCapacity() > 0 && dto.getMinAge() >= 0 && dto.getMaxAge() >= 0 && dto.getVenue() != null
                && dto.getDescription() != null);
    }
}
