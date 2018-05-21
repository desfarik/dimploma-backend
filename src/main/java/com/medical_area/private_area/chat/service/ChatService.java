package com.medical_area.private_area.chat.service;

import com.medical_area.private_area.chat.controllers.request.MessageRequest;
import com.medical_area.private_area.chat.controllers.request.UserMessageRequest;
import com.medical_area.private_area.chat.model.Message;
import com.medical_area.private_area.chat.repository.ChatRepository;
import com.medical_area.private_area.common.service.UserService;
import com.medical_area.public_area.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.medical_area.public_area.models.RoleTypes.DOCTOR;
import static com.medical_area.public_area.models.RoleTypes.PATIENT;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;

    public List<Message> getAllMessages(Integer userId) {
        User user = userService.getCurrentUser();
        if (user.getRole().getId().equals(DOCTOR)) {
            return setSeenForMessages(chatRepository.findAllByPatientIdAndDoctorId(userId, user.getId()));

        }
        if (user.getRole().getId().equals(PATIENT)) {
            return setSeenForMessages(chatRepository.findAllByPatientIdAndDoctorId(user.getId(), userId));
        }
        return Collections.emptyList();
    }

    private List<Message> setSeenForMessages(List<Message> messages) {
        messages.forEach(m -> m.setSeen(true));
        chatRepository.save(messages);
        return messages;
    }

    public void saveMessage(MessageRequest message) {
        User user = userService.getCurrentUser();
        if (user.getRole().getId().equals(DOCTOR)) {
            chatRepository.save(new Message(user.getId(), message.getUserId(), message.getData(), true, true));
        }
        if (user.getRole().getId().equals(PATIENT)) {
            chatRepository.save(new Message(message.getUserId(), user.getId(), message.getData(), false, false));
        }
    }

    public List<UserMessageRequest> getPatientsMessage() {
        User user = userService.getCurrentUser();
        Comparator<Message> comparator = Comparator.comparingInt(Message::getId);

        if (user.getRole().getId().equals(DOCTOR)) {
            return chatRepository.findAllByDoctorId(user.getId())
                    .stream().collect(Collectors.groupingBy(Message::getPatientId)).values().stream()
                    .map(messages -> messages.stream().max(comparator).get())
                    .map(m -> new UserMessageRequest(m.getPatientId(), m.getId(), !m.isSeen()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Integer> getAllNotSeenMessages() {
        User user = userService.getCurrentUser();
        if (user.getRole().getId().equals(DOCTOR)) {
            return chatRepository.findAllByDoctorIdAndSeen(user.getId(), false)
                    .stream().map(Message::getId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
