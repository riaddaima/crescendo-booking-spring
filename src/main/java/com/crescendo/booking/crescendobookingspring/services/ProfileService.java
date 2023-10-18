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

    public void createOwnProfile(String email, String firstName, String lastName, String phoneNumber, boolean isSubbed) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        Profile profile = new Profile(user, firstName, lastName, phoneNumber, isSubbed);
        System.out.println("leprofile " + profile.getUser().getId());
        profileRepository.save(profile);
    }
}
