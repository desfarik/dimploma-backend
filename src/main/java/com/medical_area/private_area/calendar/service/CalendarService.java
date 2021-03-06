package com.medical_area.private_area.calendar.service;

import com.medical_area.private_area.calendar.controllers.request.CalendarRequest;
import com.medical_area.private_area.calendar.models.Calendar;
import com.medical_area.private_area.calendar.models.CalendarUpdates;
import com.medical_area.private_area.calendar.repositories.CalendarRepository;
import com.medical_area.private_area.calendar.repositories.CalendarUpdatesRepository;
import com.medical_area.private_area.common.service.ToastService;
import com.medical_area.private_area.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CalendarService {

    @Autowired
    private UserService userService;
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private CalendarUpdatesRepository calendarUpdatesRepository;
    @Autowired
    private ToastService toastService;

    public CalendarRequest getCalendarForCurrentUser() {
        Calendar calendar = calendarRepository.findByUserId(userService.getCurrentUser().getId());
        if (calendar == null) {
            return null;
        }
        return new CalendarRequest(calendar.getData(),
                Optional.ofNullable(calendarUpdatesRepository.findFirstByCalendar(calendar)).orElse(new CalendarUpdates()).getDate());
    }

    public Boolean saveCalendar(CalendarRequest calendarRequest) {
        Integer userId = Optional.ofNullable(calendarRequest.getDoctorId()).orElse(userService.getCurrentUser().getId());
        Calendar calendar = calendarRepository.findByUserId(userId);
        if (calendar != null && checkCalendar(calendar, calendarRequest)) {
            return false;
        }
        if (calendar == null) {
            Calendar newCalendar = new Calendar(userService.getCurrentUser().getId(), calendarRequest.getData());
            calendarRepository.save(newCalendar);
            calendarUpdatesRepository.save(new CalendarUpdates(newCalendar, new Date().toString()));
        } else {
            calendar.setData(calendarRequest.getData());
            calendarRepository.save(calendar);
            calendarUpdatesRepository.save(new CalendarUpdates(calendar, new Date().toString()));
        }
        toastService.saveNotification(getLastUpdate());
        return true;
    }

    private Boolean checkCalendar(Calendar calendar, CalendarRequest calendarRequest) {
        CalendarUpdates calendarUpdates = Optional.ofNullable(calendarUpdatesRepository.findFirstByCalendar(calendar)).orElse(new CalendarUpdates(calendar, new Date().toString()));
        Boolean isNewCalendar = calendarUpdates.getDate().equals(calendarRequest.getLastUpdate());
        if (isNewCalendar) {
            calendarUpdatesRepository.delete(calendarUpdates);
        }
        return !isNewCalendar;
    }

    public Boolean checkLastUpdate(String date) {
        return calendarUpdatesRepository.findAllByDate(date).stream().anyMatch(c -> c.getDate().equals(date));
    }

    public String getLastUpdate() {
        return calendarUpdatesRepository.findFirstByCalendar(calendarRepository.findByUserId(userService.getCurrentUser().getId())).getDate();
    }

}
