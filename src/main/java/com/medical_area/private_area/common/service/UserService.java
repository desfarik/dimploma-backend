package com.medical_area.private_area.common.service;

import com.medical_area.private_area.common.model.UserPatients;
import com.medical_area.private_area.common.repository.UserPatientsRepository;
import com.medical_area.private_area.profile.models.UserDetailsRequest;
import com.medical_area.private_area.profile.repositories.UserDetailsRepository;
import com.medical_area.public_area.models.User;
import com.medical_area.public_area.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPatientsRepository userPatientsRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public User getCurrentUser() {
        String userIdentifier = authenticationFacade.getAuthentication().getPrincipal().toString();
        return userRepository.findFirstByPhoneOrUserName(userIdentifier, userIdentifier);
    }

    public List<UserDetailsRequest> getAllPatients() {
        List<Integer> patients = Optional.ofNullable(userPatientsRepository.findAllByDoctorId(getCurrentUser().getId())
                .stream().map(UserPatients::getPatientId).collect(Collectors.toList())).orElse(new ArrayList<>());
        return userDetailsRepository.findAllByIdIn(patients);
    }
}
