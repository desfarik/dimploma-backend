package com.medical_area.public_area.services;

import com.medical_area.public_area.controllers.requests.ResetPasswordRequest;
import com.medical_area.public_area.models.User;
import com.medical_area.public_area.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneVerificationService phoneVerificationService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity reset(ResetPasswordRequest request) {
        LOGGER.info("Start reset user password");
        User user = userRepository.findFirstByPhone(request.getPhoneNumber());

        if (user == null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        if (!phoneVerificationService.verify(request.getCountryCode(), request.getPhoneNumber(), request.getKey())) {
            return new ResponseEntity(HttpStatus.GONE);
        }
        user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        LOGGER.info("Update user password was successful");
        return new ResponseEntity(HttpStatus.OK);
    }
}
