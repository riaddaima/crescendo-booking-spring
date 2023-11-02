package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.entities.Booking;
import com.crescendo.booking.crescendobookingspring.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public boolean createBooking(@RequestParam Long event, @RequestParam Long plan) {
        if (!validateFields(event, plan))
            return false;
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.addBooking(email, event, plan);
    }

    @GetMapping
    public List<Booking> getBookings() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getBookings(email);
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    private boolean validateFields(Long event, Long plan) {
        return (event != null && plan != null);
    }

    @DeleteMapping
    public boolean cancelBooking(@RequestParam Long id) {
        return bookingService.cancelBooking(id);
    }
}
