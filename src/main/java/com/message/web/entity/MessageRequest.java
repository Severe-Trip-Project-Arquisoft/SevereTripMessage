package com.message.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    @NotEmpty
    private String senderId;
    @NotEmpty
    private String receiverId;
    @NotEmpty
    private String content;



}
