package com.medical_area.public_area.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Settings {

    private @Value("${account.security.api.key}") String API_KEY;

    public String getAuthyId(){
        return API_KEY;
    }
}
