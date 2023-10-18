package com.crescendo.booking.crescendobookingspring.security;

import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditor implements AuditorAware<User> {

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("Null authentication");
            return null;
        }

        String email = (String)authentication.getPrincipal();
        System.out.println("Email: " + email);
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
