package com.message.web.repository;

import com.message.web.entity.Mailbox;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;


@Repository
public interface MailboxRepository extends MongoRepository<Mailbox, Serializable> {


    Optional<Mailbox> findByClientId(String sender);
}
