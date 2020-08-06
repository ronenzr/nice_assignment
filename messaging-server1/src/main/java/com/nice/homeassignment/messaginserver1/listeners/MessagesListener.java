package com.nice.homeassignment.messaginserver1.listeners;

import com.nice.homeassignment.messaginserver1.listeners.interfaces.Listener;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import com.nice.homeassignment.messaginserver1.services.handlers.MessageHandler;
import com.nice.homeassignment.messaginserver1.services.interfaces.Handler;
import org.springframework.stereotype.Component;

@Component
public class MessagesListener implements Listener<Message> {
    private boolean isProcessing;
    private Handler<Message> handler;

    public MessagesListener(MessageHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean isProcessing() {
        return this.isProcessing;
    }

    @Override
    public void handle(Message message) {
        this.isProcessing = true;
        this.handler.process(message);
        this.isProcessing = false;
    }
}
