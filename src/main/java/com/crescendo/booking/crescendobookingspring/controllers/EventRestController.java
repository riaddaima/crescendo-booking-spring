package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Event;
import com.crescendo.booking.crescendobookingspring.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/event")
public class EventRestController {

    @Autowired
    EventService eventService;

    @PostMapping
    public boolean createEvent(@RequestBody Event dto) {
        if (!validateFields(dto))
            return false;
        return eventService.createEvent(dto);
    }

    @GetMapping("/all")
    public List<com.crescendo.booking.crescendobookingspring.data.entities.Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public com.crescendo.booking.crescendobookingspring.data.entities.Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @DeleteMapping
    public boolean deleteEvent(@RequestParam Long id) {
        return eventService.deleteEvent(id);
    }

    @PutMapping
    public boolean updateEvent(@RequestParam Long id, @RequestBody Event dto) {
        if (!validateFields(dto))
            return false;
        return eventService.updateEvent(id, dto);
    }

    private boolean validateFields(Event dto) {
        return (dto.getName() != null && dto.getStartTime() != null && dto.getEndTime() != null
        && Long.parseLong(dto.getEndTime()) >= Long.parseLong(dto.getStartTime()) && dto.getDate() != null
                && dto.getCapacity() > 0 && dto.getMinAge() >= 0 && dto.getMaxAge() >= 0 && dto.getVenue() != null
                && dto.getDescription() != null && dto.getInstructors() != null && !dto.getInstructors().isEmpty());
    }
}
