package com.nice.homeassignment.messaginserver1.services;

import com.nice.homeassignment.messaginserver1.factories.MessageFactory;
import com.nice.homeassignment.messaginserver1.factories.interfaces.Factory;
import com.nice.homeassignment.messaginserver1.listeners.MessagesListener;
import com.nice.homeassignment.messaginserver1.listeners.interfaces.Listener;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import com.nice.homeassignment.messaginserver1.queues.Queue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MessagesService {

    public static final String MESSAGE_SERVICE_QUEUE_NAME = "MessageService";
    private QueuesService queuesService;
    private Listener messagesListener;
    private Factory<Message, String> messageFactory;

    public MessagesService(QueuesService queuesService,
                           MessageFactory messageFactory,
                           MessagesListener messagesListener) {
        this.queuesService = queuesService;
        this.messagesListener = messagesListener;
        this.messageFactory = messageFactory;
    }

    @PostConstruct
    public void init(){
        Queue messageQueue = new Queue(MESSAGE_SERVICE_QUEUE_NAME);
        queuesService.addQueue(messageQueue);
        queuesService.addListener(messageQueue.getName(), messagesListener);
    }


    public void addMessage(String message){
        Message toSend = messageFactory.get(message);
        queuesService.add(MESSAGE_SERVICE_QUEUE_NAME, toSend);
    }
}
