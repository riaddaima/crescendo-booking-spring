package com.crescendo.booking.crescendobookingspring.services;

import com.crescendo.booking.crescendobookingspring.data.entities.Profile;
import com.crescendo.booking.crescendobookingspring.data.repositories.ProfileRepository;
import com.crescendo.booking.crescendobookingspring.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crescendo.booking.crescendobookingspring.data.entities.User;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    public void createOwnProfile(String email, String firstName, String lastName, String phoneNumber, Boolean isSubbed) {
        User user = userRepository.findByEmail(email);
        Profile profile = new Profile(user, firstName, lastName, phoneNumber, isSubbed);
        profileRepository.save(profile);
    }

    public Profile getOwnProfile(String email) {
        User user = userRepository.findByEmail(email);
        return user.getProfile();
    }

    public void updateOwnProfile(String email, String firstName, String lastName, String phoneNumber, Boolean isSubbed) {
        User user = userRepository.findByEmail(email);
        Profile profile = user.getProfile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhoneNumber(phoneNumber);
        profile.setIsSubbed(isSubbed);
        profileRepository.save(profile);
    }
}
