package com.message.web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "Conversation")
public class Conversation implements Serializable {

    @Id
    @NotNull
    private String id;
    private Date start;
    private List<Message> messages;

    public Conversation() {

        this.start = Date.from(Instant.now());
        this.messages = new ArrayList<>();

    }
}
