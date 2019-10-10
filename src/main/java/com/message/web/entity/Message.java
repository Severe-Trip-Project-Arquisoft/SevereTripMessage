package com.message.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String content;
    private String sender;
    private Date date;

    public Message(MessageRequest messageRequest) {
        this.content = messageRequest.getContent();
        this.sender = messageRequest.getSenderId();
        this.date = Date.from(Instant.now());

    }
}
