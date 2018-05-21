package com.medical_area.private_area.common.service;

import com.medical_area.private_area.calendar.service.CalendarService;
import com.medical_area.private_area.chat.service.ChatService;
import com.medical_area.private_area.common.controller.request.ToastRequest;
import com.medical_area.private_area.common.model.Notifications;
import com.medical_area.private_area.common.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToastService {

    @Autowired
    private ChatService chatService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserService userService;


    public ToastRequest check(String date) {
        Integer userId = userService.getCurrentUser().getId();
        List<Integer> messages = chatService.getAllNotSeenMessages();
        if (!notificationRepository.existsByIdentityAndUserId(messages.toString(), userId)) {
            notificationRepository.save(new Notifications(messages.toString(), userId));
            return new ToastRequest("chat");
        }
        Boolean isValidDate = calendarService.checkLastUpdate(date);
        String validDate = calendarService.getLastUpdate();
        if (!isValidDate && !notificationRepository.existsByIdentityAndUserId(validDate, userId)) {
            notificationRepository.save(new Notifications(validDate, userId));
            return new ToastRequest("calendar");
        }
        return new ToastRequest("");
    }

    public void saveNotification(String data) {
        notificationRepository.save(new Notifications(data, userService.getCurrentUser().getId()));
    }
}