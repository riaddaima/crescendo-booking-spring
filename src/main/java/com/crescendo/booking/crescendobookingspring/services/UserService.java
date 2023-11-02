package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import com.crescendo.booking.crescendobookingspring.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean changePassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return false;
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return false;
        userRepository.delete(user);
        return true;
    }
}
