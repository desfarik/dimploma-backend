package com.medical.area.public_area.services;

import com.medical.area.public_area.controllers.requests.UserRegisterRequest;
import com.medical.area.public_area.models.User;
import com.medical.area.public_area.repositories.UserRepository;
import com.medical.area.public_area.repositories.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.medical.area.public_area.models.RoleTypes.DOCTOR;

@Service
public class RegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PhoneVerificationService phoneVerificationService;

    public ResponseEntity register(UserRegisterRequest request) {
        LOGGER.info("Start register new user");
        if (userRepository.existsUserByPhone(request.getUsername())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        if (userRepository.existsUserByUserName(request.getUsername())) {
            return new ResponseEntity(HttpStatus.REQUEST_TIMEOUT);
        }
        if (!phoneVerificationService.verify(request.getCountryCode(), request.getPhoneNumber(), request.getKey())) {
            return new ResponseEntity(HttpStatus.GONE);
        }
        userRepository.save(new User(request.getUsername(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                request.getPhoneNumber(),
                request.getCountryCode(),
                userRoleRepository.findUserRoleById(DOCTOR)
        ));

        LOGGER.info("New user registered successful");
        return new ResponseEntity(HttpStatus.OK);
    }
}
