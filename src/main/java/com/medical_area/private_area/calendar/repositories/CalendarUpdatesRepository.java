package com.medical_area.private_area.calendar.repositories;

import com.medical_area.private_area.calendar.models.Calendar;
import com.medical_area.private_area.calendar.models.CalendarUpdates;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface CalendarUpdatesRepository extends CrudRepository<CalendarUpdates, Integer> {

    CalendarUpdates findFirstByCalendar(Calendar calendarId);

    Boolean existsByDate(String date);

    List<CalendarUpdates> findAllByDate(String date);
}
