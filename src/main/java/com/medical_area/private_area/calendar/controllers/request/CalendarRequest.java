package com.medical_area.private_area.calendar.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalendarRequest {
    private Integer doctorId;
    @NotNull
    private String data;
    @NotNull
    private String lastUpdate;

    public CalendarRequest(String data, String lastUpdate) {
        this.data = data;
        this.lastUpdate = lastUpdate;
    }
}
