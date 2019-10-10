package com.message.web.service;


import com.message.web.entity.Conversation;
import com.message.web.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {


    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }



    public Conversation getById(String id) {

        Optional<Conversation> conversation = this.conversationRepository.findById(id);
        return conversation.orElseGet(null);


    }

    public Conversation createConversation() {

        Conversation conversation =  this.conversationRepository.save(new Conversation());
        return conversation;
    }

    public String saveConversation(Conversation conversation){
        return this.conversationRepository.save(conversation).getId();
    }


}
