package com.medical_area.private_area.calendar.controllers;

import com.medical_area.private_area.calendar.controllers.request.CalendarRequest;
import com.medical_area.private_area.calendar.controllers.request.UpdateDateRequest;
import com.medical_area.private_area.calendar.service.CalendarService;
import com.medical_area.private_area.common.controller.request.ToastRequest;
import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/calendar")
public class CalendarRsService {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public CalendarRequest getCalendar() {
        return calendarService.getCalendarForCurrentUser();
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ToastRequest saveCalendar(@Valid @RequestBody CalendarRequest calendar) {
        if (calendarService.saveCalendar(calendar)) {
            return new ToastRequest(calendarService.getLastUpdate());
        }
        throw new ExecutionException("ERROR saving CALENDAR");
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Boolean checkLastUpdate(@Valid @RequestBody UpdateDateRequest date) {
        return calendarService.checkLastUpdate(date.getLastUpdate());
    }
}
