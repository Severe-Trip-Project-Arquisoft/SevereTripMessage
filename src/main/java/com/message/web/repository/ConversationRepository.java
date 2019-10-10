package com.message.web.repository;

import com.message.web.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, Serializable>{


}
