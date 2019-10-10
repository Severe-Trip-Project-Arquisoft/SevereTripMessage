package com.message.web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class ConversationResponse {
    private String sender;
    private String receiver;
    private Date start;
    private List<Message> messages;


    public ConversationResponse(Conversation conversation, String sender, String receiver) {

        this.sender = sender;
        this.receiver = receiver;
        this.start = conversation.getStart();
        this.messages = conversation.getMessages();

    }
}
