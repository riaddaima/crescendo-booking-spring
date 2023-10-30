package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Booking;
import com.crescendo.booking.crescendobookingspring.data.entities.Event;
import com.crescendo.booking.crescendobookingspring.data.entities.Plan;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.BookingRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.EventRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.PlanRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PlanRepository planRepository;

    public boolean addBooking(String email, long eventId, long planId) {
        User user = userRepository.findByEmail(email);
        System.out.println("Here");
        Optional<Event> event = eventRepository.findById(eventId);
        Optional<Plan> plan = planRepository.findById(planId);

        if (event.isPresent() && plan.isPresent()) {
            System.out.println("found " + event + " and " + plan);
            boolean isWaitList = bookingRepository.isWaitList(eventId);
            bookingRepository.save(new Booking(user, event.get(), isWaitList, plan.get()));
            return true;
        }
        return false;
    }
}
