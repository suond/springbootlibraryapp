package com.book.springbootlibrary.controller;

import com.book.springbootlibrary.entity.Message;
import com.book.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.book.springbootlibrary.service.MessageService;
import com.book.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(JwtAuthenticationToken token, @RequestBody Message messageRequest)  {
        String userEmail = token.getToken().getSubject();
        messageService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(JwtAuthenticationToken token, @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = token.getToken().getSubject();

        String admin = (String)token.getToken().getClaims().get("userType");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only");
        }
        messageService.putMessage(adminQuestionRequest,userEmail);
    }
}
