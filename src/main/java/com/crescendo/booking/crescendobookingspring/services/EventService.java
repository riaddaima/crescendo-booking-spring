package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Event;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crescendo.booking.crescendobookingspring.format.DateHelper.convertTimestampToLocalTime;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    public boolean createEvent(com.crescendo.booking.crescendobookingspring.data.dtos.Event dto) {
        LocalTime startTime = convertTimestampToLocalTime(dto.getStartTime());
        LocalTime endTime = convertTimestampToLocalTime(dto.getEndTime());
        Date date = new Date(Long.parseLong(dto.getDate()));

        List<User> instructors = new ArrayList<>();
        for (String email : dto.getInstructors())
            instructors.add(userRepository.findByEmail(email));
        Event event = new Event(dto.getName(), startTime, endTime, date, dto.getCapacity(), dto.getMinAge(),
                dto.getMaxAge(), dto.getVenue(), dto.getDescription(), instructors);
        eventRepository.save(event);
        return true;
    }
}
