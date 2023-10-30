package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private boolean validateFields(Long event, Long plan) {
        return (event != null && plan != null);
    }
}
