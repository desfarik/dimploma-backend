package com.medical_area.public_area.services;

import com.authy.AuthyApiClient;
import com.authy.api.Params;
import com.authy.api.Verification;
import com.medical_area.public_area.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhoneVerificationService {

    private AuthyApiClient authyApiClient;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public PhoneVerificationService(AuthyApiClient authyApiClient) {
        this.authyApiClient = authyApiClient;
    }

    public ResponseEntity start(String countryCode, String phoneNumber, String via, boolean needExictingPhone) {
        if (userRepository.existsUserByPhone(phoneNumber) != needExictingPhone) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Params params = new Params();
        params.setAttribute("code_length", "4");
        Verification verification = authyApiClient
                .getPhoneVerification()
                .start(phoneNumber, countryCode, via, params);

        if (!verification.isOk()) {
            return new ResponseEntity(HttpStatus.GONE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public boolean verify(String countryCode, String phoneNumber, String token) {
        Verification verification = authyApiClient
                .getPhoneVerification()
                .check(phoneNumber, countryCode, token);
        return verification.isOk();
    }
}
