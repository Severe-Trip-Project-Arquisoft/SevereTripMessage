package com.message.web.controller;



import com.message.web.entity.ConversationResponse;
import com.message.web.entity.MessageRequest;
import com.message.web.service.MailboxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value="Messages", produces = "application/json", description = "Messages sent between users and stored in a MongoDB database.")
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MailboxController {



    private MailboxService mailboxService;

    @Autowired
    public MailboxController(MailboxService mailboxService) {
        this.mailboxService = mailboxService;
    }

    @ApiOperation(value = "Get all the conversations involving the user specified with the given Id." , response = List.class)
    @GetMapping("/{clientId}")
    public ResponseEntity<List<ConversationResponse>> getConversations(
            @ApiParam(value = "Mailbox Owner's ID.", required = true)
            @PathVariable String clientId){
        return ResponseEntity.ok(mailboxService.getConversations(clientId));
    }

    @ApiOperation(value = "Creates and stores a message involving two users. Mailboxes and conversations are created if needed. Returns the ID of the conversation object." , response = String.class)
    @PostMapping("/")
    public String sendMessage(
            @ApiParam(value = "Message JSON Object with Sender's Id, Receiver's ID and message content.", required = true)
            @RequestBody @Valid MessageRequest messageRequest){
        return mailboxService.sendMessage(messageRequest);
    }
}
