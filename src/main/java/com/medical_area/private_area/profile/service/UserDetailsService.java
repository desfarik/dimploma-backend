package com.medical_area.private_area.profile.service;

import com.medical_area.private_area.common.service.UserService;
import com.medical_area.private_area.profile.models.UserDetailsRequest;
import com.medical_area.private_area.profile.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    UserService userService;

    public UserDetailsRequest getUserDetails() {
        return Optional.ofNullable(userDetailsRepository.findFirstById(userService.getCurrentUser().getId()))
                .orElse(new UserDetailsRequest());
    }

    public void save(UserDetailsRequest userDetails) {
        userDetailsRepository.save(new UserDetailsRequest(userService.getCurrentUser().getId(), userDetails.getDetails()));
    }
}
