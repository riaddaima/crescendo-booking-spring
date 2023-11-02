package com.crescendo.booking.crescendobookingspring.controllers;

import com.crescendo.booking.crescendobookingspring.data.dtos.User;
import com.crescendo.booking.crescendobookingspring.security.jwt.JwtHelper;
import com.crescendo.booking.crescendobookingspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @GetMapping
    public com.crescendo.booking.crescendobookingspring.data.entities.User getUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(email);
    }

    @GetMapping("/all")
    public List<com.crescendo.booking.crescendobookingspring.data.entities.User> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping
    public boolean deleteUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.deleteUser(email);
    }

    @PatchMapping
    public boolean changePassword(@RequestBody User dto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!validateFields(dto))
            return false;
        return userService.changePassword(email, dto.getPassword());
    }

    @PostMapping
    public boolean createUser(@RequestBody User dto) {
        if (!validateFields(dto))
            return false;
        return userService.createCustomer(dto.getEmail(), dto.getPassword());
    }

    private boolean validateFields(User dto) {
        return (dto.getEmail() != null && isValidEmail(dto.getEmail())
                && dto.getPassword() != null);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
