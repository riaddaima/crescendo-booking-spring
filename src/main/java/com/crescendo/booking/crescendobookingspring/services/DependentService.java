package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Dependent;
import com.crescendo.booking.crescendobookingspring.data.entities.User;
import com.crescendo.booking.crescendobookingspring.data.repositories.DependentRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public List<Dependent> getDependents(String email) {
        User user = userRepository.findByEmail(email);
        return user.getDependents();
    }

    public boolean deleteDependent(long id) {
        Dependent dependent = dependentRepository.findById(id).orElse(null);
        if (dependent == null)
            return false;
        dependentRepository.delete(dependent);
        return true;
    }

    public boolean updateDependent(long id, com.crescendo.booking.crescendobookingspring.data.dtos.Dependent dto) {
        Dependent dependent = dependentRepository.findById(id).orElse(null);
        if (dependent == null)
            return false;
        dependent.setFirstName(dto.getFirstName());
        dependent.setLastName(dto.getLastName());
        dependent.setDob(dto.getDob());
        dependent.setGender(dto.getGender());
        dependentRepository.save(dependent);
        return true;
    }
}
