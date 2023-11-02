package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.entities.Attend;
import com.crescendo.booking.crescendobookingspring.services.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/attend")
public class AttendRestController {

    @Autowired
    AttendService attendService;

    @PostMapping
    public boolean createAttend(@RequestBody List<Long> dependents, @RequestParam Long booking) {
        if (!validateFields(dependents, booking))
            return false;
        return attendService.addAttendances(dependents, booking);
    }

    @GetMapping
    public List<Attend> getAttendances() {
        return attendService.getAllAttendances();
    }

    private boolean validateFields(List<Long> dependents, Long booking) {
        return (dependents != null && booking != null);
    }
}
