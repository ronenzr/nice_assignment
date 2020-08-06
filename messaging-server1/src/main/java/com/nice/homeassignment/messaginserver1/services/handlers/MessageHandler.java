package com.nice.homeassignment.messaginserver1.services.handlers;

import com.nice.homeassignment.messaginserver1.clients.Server2Client;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import com.nice.homeassignment.messaginserver1.services.interfaces.Handler;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler implements Handler<Message> {

    private Server2Client server2Client;

    public MessageHandler(Server2Client server2Client) {
        this.server2Client = server2Client;
    }

    @Override
    public void process(Message value) {
        server2Client.sendMessage(value);
    }
}
