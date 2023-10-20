package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Dependent;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.DependentRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DependentService {

    @Autowired
    DependentRepository dependentRepository;

    @Autowired
    UserRepository userRepository;

    public void addOwnDependent(String email, String firstName, String lastName, Date dob, String gender) {
        User user = userRepository.findByEmail(email);
        Dependent dependent = new Dependent(user, firstName, lastName, dob, gender);
        dependentRepository.save(dependent);
    }
}
