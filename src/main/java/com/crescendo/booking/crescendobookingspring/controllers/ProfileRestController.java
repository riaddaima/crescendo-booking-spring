package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.Profile;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import com.crescendo.booking.crescendobookingspring.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/profile")
public class ProfileRestController {
    @Autowired
    ProfileService profileService;

    @PostMapping
    public boolean createProfile(@RequestBody Profile dto) {
        if (!validateFields(dto))
            return false;
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profileService.createOwnProfile(email, dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber(), dto.getIsSubbed());
        return true;
    }

    @PatchMapping
    public boolean updateProfile(@RequestBody Profile dto) {
        if (!validateFields(dto))
            return false;
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profileService.updateOwnProfile(email, dto.getFirstName(), dto.getLastName(), dto.getPhoneNumber(), dto.getIsSubbed());
        return true;
    }

    private boolean validateFields(Profile dto) {
        return (dto.getFirstName() != null && dto.getLastName() != null && dto.getPhoneNumber() != null &&
                dto.getIsSubbed() != null);
    }
}
