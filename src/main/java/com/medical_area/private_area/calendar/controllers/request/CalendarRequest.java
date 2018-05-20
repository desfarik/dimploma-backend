package com.medical_area.private_area.calendar.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalendarRequest {
    private Integer doctorId;
    @NotNull
    private String data;
    @NotNull
    private Date lastUpdate;

    public CalendarRequest(String data, Date lastUpdate) {
        this.data = data;
        this.lastUpdate = lastUpdate;
    }
}
