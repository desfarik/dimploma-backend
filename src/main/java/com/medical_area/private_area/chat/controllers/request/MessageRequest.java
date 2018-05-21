package com.medical_area.private_area.chat.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageRequest {
    private Integer userId;
    private String data;
}
