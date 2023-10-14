package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.User;
import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import com.crescendo.booking.crescendobookingspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        return JwtHelper.generateToken(authentication);
    }

    @PostMapping
    public boolean createUser(@RequestBody User dto) {
        if (validateFields(dto))
            return false;
        userService.createCustomer(dto.getEmail(), dto.getPassword());
        return true;
    }

    private boolean validateFields(User dto) {
        return (dto.getEmail() != null && dto.getPassword() != null);
    }

}
