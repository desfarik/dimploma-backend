package com.medical.area.public_area.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordRequest {
    @NotNull
    private String newPassword;
    @NotNull
    private String countryCode;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String key;
}
