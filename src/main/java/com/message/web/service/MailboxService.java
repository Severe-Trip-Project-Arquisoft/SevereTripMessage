package com.message.web.service;


import com.message.web.entity.*;
import com.message.web.repository.MailboxRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class MailboxService {

    private MailboxRepository mailboxRepository;
    private ConversationService conversationService;


    @Autowired
    public MailboxService(MailboxRepository mailboxRepository, ConversationService conversationService) {
        this.mailboxRepository = mailboxRepository;
        this.conversationService = conversationService;
    }

    //4347-19-002767989

    public List<ConversationResponse> getConversations(String clientId) {

        List<ConversationResponse> conversations = new ArrayList<>();
        Optional<Mailbox> m = this.mailboxRepository.findByClientId(clientId);



        if (m.isPresent()) {
            Mailbox mailbox = m.get();
            for (Map.Entry<String, String> entry : mailbox.getContacts().entrySet()) {
                conversations.add( new ConversationResponse(this.conversationService.getById(entry.getValue()), clientId, entry.getKey()));
            }
        }
        return conversations;
    }


    public String sendMessage(MessageRequest messageRequest) {

        String sender = messageRequest.getSenderId();
        String receiver = messageRequest.getReceiverId();

        Optional<Mailbox> msender = mailboxRepository.findByClientId(sender);
        Optional<Mailbox> mreceiver = mailboxRepository.findByClientId(receiver);

        Mailbox s_mailbox, r_mailbox;

        if (!msender.isPresent()) {
            s_mailbox = new Mailbox(sender);
            this.mailboxRepository.save(s_mailbox);
        } else {
            s_mailbox = msender.get();
        }
        if (!mreceiver.isPresent()) {
            r_mailbox = new Mailbox(receiver);
            this.mailboxRepository.save(s_mailbox);
        } else {
            r_mailbox = mreceiver.get();
        }

        Conversation conversation;
        if (!s_mailbox.getContacts().containsKey(receiver)) {
            conversation = this.conversationService.createConversation();
            s_mailbox.getContacts().put(receiver, conversation.getId());
            this.mailboxRepository.save(s_mailbox);
        }
        else
        {
            String id = s_mailbox.getContacts().get(receiver);
            conversation = this.conversationService.getById(id);
            if(conversation == null) return "Operation failed. Something missing.";
        }
        if (!r_mailbox.getContacts().containsKey(sender)) {
            r_mailbox.getContacts().put(sender, conversation.getId());
            this.mailboxRepository.save(r_mailbox);
        }

        Message message = new Message(messageRequest);
        conversation.getMessages().add(message);

        return this.conversationService.saveConversation(conversation);


    }
}
