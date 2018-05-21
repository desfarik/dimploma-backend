package com.medical_area.private_area.chat.controllers;

import com.medical_area.private_area.chat.controllers.request.MessageRequest;
import com.medical_area.private_area.chat.controllers.request.UserMessageRequest;
import com.medical_area.private_area.chat.model.Message;
import com.medical_area.private_area.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ChatRsService {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "chat/messages/get/{patientId}", method = RequestMethod.GET)
    public List<Message> getAllMessages(@PathVariable Integer patientId) {
        return chatService.getAllMessages(patientId);
    }

    @RequestMapping(value = "chat/messages/users", method = RequestMethod.GET)
    public List<UserMessageRequest> getPatientsMessage() {
        return chatService.getPatientsMessage();
    }

    @RequestMapping(value = "chat/messages/send", method = RequestMethod.POST)
    public ResponseEntity saveMessage(@RequestBody MessageRequest message) {
        chatService.saveMessage(message);
        return new ResponseEntity(HttpStatus.OK);
    }
}
