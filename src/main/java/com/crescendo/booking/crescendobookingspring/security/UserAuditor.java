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
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }
            String email = (String)authentication.getPrincipal();
            return Optional.ofNullable(userRepository.findByEmail(email));
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return Optional.empty();
        }
    }
}
