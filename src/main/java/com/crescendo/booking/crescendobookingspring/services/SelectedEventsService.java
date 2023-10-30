package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.dtos.SelectedEvents;
import com.crescendo.booking.crescendobookingspring.data.entities.Event;
import com.crescendo.booking.crescendobookingspring.data.entities.Plan;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SelectedEventsService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlanRepository planRepository;

    @Cacheable(value = "selectedEvents")
    public SelectedEvents getSelectedEvents(long userId) {
        return new SelectedEvents();
    }

    @CachePut(value = "selectedEvents", key = "#userId")
    public SelectedEvents addEvent(SelectedEvents selectedEvents, long userId, long eventId, long planId) {
        Event event = eventRepository.findById(eventId).get();
        Plan plan = planRepository.findById(planId).get();
        selectedEvents.addEvent(event, plan);
        return selectedEvents;
    }

    @CachePut(value = "selectedEvents", key = "#userId")
    public SelectedEvents removeEvent(SelectedEvents selectedEvents, long userId, long eventId) {
        Event event = eventRepository.findById(eventId).get();
        selectedEvents.removeEvent(event);
        return selectedEvents;
    }

    @CachePut(value = "selectedEvents", key = "#userId")
    public SelectedEvents empty(SelectedEvents selectedEvents, long userId) {
        selectedEvents.empty();
        return selectedEvents;
    }
}
