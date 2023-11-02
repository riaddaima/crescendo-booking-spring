package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Dependent;
import com.crescendo.booking.crescendobookingspring.services.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/dependent")
public class DependentRestController {

    @Autowired
    DependentService dependentService;

    @PostMapping
    public boolean addDependent(@RequestBody Dependent dto) {
        if (!validateFields(dto))
            return false;
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dependentService.addOwnDependent(email, dto.getFirstName(), dto.getLastName(), dto.getDob(), dto.getGender());
        return true;
    }

    @GetMapping
    public List<com.crescendo.booking.crescendobookingspring.data.entities.Dependent> getDependents() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dependentService.getDependents(email);
    }

    private boolean validateFields(Dependent dto) {
        return (dto.getFirstName() != null && dto.getLastName() != null && dto.getDob() != null &&
                dto.getGender() != null);
    }
}
