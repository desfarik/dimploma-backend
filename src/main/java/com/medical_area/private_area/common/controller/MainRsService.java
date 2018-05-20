package com.medical_area.private_area.common.controller;

import com.medical_area.private_area.calendar.controllers.request.CalendarRequest;
import com.medical_area.private_area.common.service.UserService;
import com.medical_area.private_area.profile.models.UserDetailsRequest;
import com.medical_area.private_area.profile.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class MainRsService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

/*    @RequestMapping(value = "patients/get", method = RequestMethod.GET)
    public CalendarRequest getCalendar() {
        return calendarService.getCalendarForCurrentUser();
    }*/

    @RequestMapping(value = "profile/get", method = RequestMethod.GET)
    public UserDetailsRequest getUserDetails() {
        return userDetailsService.getUserDetails();
    }

    @RequestMapping(value = "profile/save", method = RequestMethod.POST)
    public ResponseEntity saveUserDetails(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        userDetailsService.save(userDetailsRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "patients/get", method = RequestMethod.GET)
    public List<UserDetailsRequest> saveUserDetails() {
        return userService.getAllPatients();
    }

/*    @RequestMapping(value = "toast/check", method = RequestMethod.POST)
    public Boolean checkLastUpdate(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        return calendarService.checkLastUpdate(date.getLastUpdate());
    }*/
}