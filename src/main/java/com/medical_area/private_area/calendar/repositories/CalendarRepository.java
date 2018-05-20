package com.medical_area.private_area.calendar.repositories;

import com.medical_area.private_area.calendar.models.Calendar;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendar, Integer> {
    Calendar findByUserId(Integer userId);
}
