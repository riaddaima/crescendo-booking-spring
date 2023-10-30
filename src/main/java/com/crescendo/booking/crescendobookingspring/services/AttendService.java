package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Attend;
import com.crescendo.booking.crescendobookingspring.data.entities.Booking;
import com.crescendo.booking.crescendobookingspring.data.entities.Dependent;
import com.crescendo.booking.crescendobookingspring.data.repositories.AttendRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.BookingRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.DependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DependentRepository dependentRepository;

    @Autowired
    AttendRepository attendRepository;

    public boolean addAttendances(List<Long> dependents, Long booking) {
        Optional<Booking> foundBooking = bookingRepository.findById(booking);
        if (foundBooking.isPresent()) {
            List<Attend> attend = new ArrayList<>();
            for (Long dependentId : dependents) {
                Optional<Dependent> dependent = dependentRepository.findById(dependentId);
                dependent.ifPresent(value -> attend.add(new Attend(foundBooking.get(), value, false)));
            }
            attendRepository.saveAll(attend);
            return true;
        }
        return false;
    }
}
