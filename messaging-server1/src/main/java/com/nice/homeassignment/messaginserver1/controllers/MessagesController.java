package com.nice.homeassignment.messaginserver1.controllers;

import com.nice.homeassignment.messaginserver1.services.MessagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/messages")
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }

    @GetMapping
    public void addMessage(String messageValue){
        messagesService.addMessage(messageValue);
    }
}
