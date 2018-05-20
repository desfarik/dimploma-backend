package com.medical_area.private_area.calendar.controllers;

import com.medical_area.private_area.calendar.controllers.request.CalendarRequest;
import com.medical_area.private_area.calendar.controllers.request.UpdateDateRequest;
import com.medical_area.private_area.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity saveCalendar(@Valid @RequestBody CalendarRequest calendar) {
        return calendarService.saveCalendar(calendar) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Boolean checkLastUpdate(@Valid @RequestBody UpdateDateRequest date) {
        return calendarService.checkLastUpdate(date.getLastUpdate());
    }
}
