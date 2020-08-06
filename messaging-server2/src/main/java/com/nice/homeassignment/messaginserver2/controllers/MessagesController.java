package com.nice.homeassignment.messaginserver2.controllers;

import com.nice.homeassignment.messaginserver2.messages.NumberMessage;
import com.nice.homeassignment.messaginserver2.services.MessagesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1/messages")
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }

    @PostMapping
    public void addMessage(@RequestBody NumberMessage message){
        messagesService.handleMessage(message);
    }
}
