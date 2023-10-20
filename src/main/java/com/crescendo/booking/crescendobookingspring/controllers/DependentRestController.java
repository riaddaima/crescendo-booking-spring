package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Dependent;
import com.crescendo.booking.crescendobookingspring.services.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private boolean validateFields(Dependent dto) {
        return (dto.getFirstName() != null && dto.getLastName() != null && dto.getDob() != null &&
                dto.getGender() != null);
    }
}
