package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import com.crescendo.booking.crescendobookingspring.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean createCustomer(String email, String password) {
        if (userRepository.findByEmail(email) != null)
            return false;
        userRepository.save(new User(email, password, Role.CUSTOMER));
        return true;
    }
}
