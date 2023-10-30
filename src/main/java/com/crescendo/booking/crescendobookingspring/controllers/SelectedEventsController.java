package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.SelectedEvents;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import com.crescendo.booking.crescendobookingspring.services.SelectedEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/selected-events")
public class SelectedEventsController {
    @Autowired
    private SelectedEventsService selectedEventsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Object getSelectedEvents() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email);
        long userId = user.getId();
        return selectedEventsService.getSelectedEvents(userId);
    }

    @PatchMapping("/add-event")
    public SelectedEvents addEvent(@RequestParam long eventId, @RequestParam long planId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email);
        long userId = user.getId();
        return selectedEventsService.addEvent(selectedEventsService.getSelectedEvents(userId), userId, eventId, planId);
    }


    @PatchMapping("/remove-event")
    public SelectedEvents removeEvent(@RequestParam long eventId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email);
        long userId = user.getId();
        return selectedEventsService.removeEvent(selectedEventsService.getSelectedEvents(userId), userId, eventId);
    }
}
