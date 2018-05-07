package com.medical.area.public_area.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String countryCode;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String key;
}
