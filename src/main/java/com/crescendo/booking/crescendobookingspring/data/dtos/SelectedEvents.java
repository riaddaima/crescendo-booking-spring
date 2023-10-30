package com.crescendo.booking.crescendobookingspring.data.dtos;

import com.crescendo.booking.crescendobookingspring.data.entities.Event;
import com.crescendo.booking.crescendobookingspring.data.entities.Plan;

import java.io.Serializable;
import java.util.Hashtable;

public class SelectedEvents implements Serializable {

    private Hashtable<Event, Plan> selectedEvents;

    public SelectedEvents() {
        this.selectedEvents = new Hashtable<>();
    }

    public void addEvent(Event event, Plan plan) {
        this.selectedEvents.put(event, plan);
    }

    public void removeEvent(Event event) {
        this.selectedEvents.remove(event);
    }

    public void updateEvent(Event event, Plan plan) {
        this.selectedEvents.replace(event, plan);
    }

    public Hashtable<Event, Plan> getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(Hashtable<Event, Plan> selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    public boolean isEmpty() {
        return this.selectedEvents.isEmpty();
    }

    public void empty() {
        this.selectedEvents.clear();
    }
}
