package com.book.springbootlibrary.service;

import com.book.springbootlibrary.dao.MessageRepository;
import com.book.springbootlibrary.entity.Message;
import com.book.springbootlibrary.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void postMessage(Message messageRequest, String userEmail){
        Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }

    public void putMessage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception{
        Optional<Message> message = messageRepository.findById(adminQuestionRequest.getId());
        if (!message.isPresent()){
            throw new Exception("Message not found!");
        }
        Message m = message.get();
        m.setAdminEmail(userEmail);
        m.setResponse(adminQuestionRequest.getResponse());
        m.setClosed(true);
        messageRepository.save(m);
    }
}
