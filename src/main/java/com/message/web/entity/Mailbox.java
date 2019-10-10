package com.message.web.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Mailbox")
public class Mailbox implements Serializable {


    @Id
    private String Id;
    @NotBlank
    private String clientId;
    private HashMap<String, String> contacts;
    private Date signDate;

    public Mailbox(String senderId) {
        this.clientId = senderId;
        this.contacts = new HashMap<>();
        this.signDate = Date.from(Instant.now());
    }


    /*User chat preferences. Goes here*/



}
