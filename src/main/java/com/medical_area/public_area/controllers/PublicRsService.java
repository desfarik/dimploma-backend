package com.medical_area.public_area.controllers;

import com.medical_area.public_area.controllers.requests.PhoneVerificationStartRequest;
import com.medical_area.public_area.controllers.requests.UserRegisterRequest;
import com.medical_area.public_area.services.PhoneVerificationService;
import com.medical_area.public_area.services.RegisterService;
import com.medical_area.public_area.controllers.requests.ResetPasswordRequest;
import com.medical_area.public_area.services.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PublicRsService {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private PhoneVerificationService phoneVerificationService;
    @Autowired
    private ResetService resetService;

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody UserRegisterRequest newUserRequest) {
        return registerService.register(newUserRequest);
    }

    @RequestMapping(path = "phone-verification", method = RequestMethod.POST)
    public ResponseEntity start(@Valid @RequestBody PhoneVerificationStartRequest requestBody) {
        return phoneVerificationService.start(requestBody.getCountryCode(), requestBody.getPhoneNumber(), requestBody.getVia(), requestBody.isReset());
    }

    @RequestMapping(path = "reset", method = RequestMethod.POST)
    public ResponseEntity reset(@Valid @RequestBody ResetPasswordRequest requestBody) {
        return resetService.reset(requestBody);
    }
}