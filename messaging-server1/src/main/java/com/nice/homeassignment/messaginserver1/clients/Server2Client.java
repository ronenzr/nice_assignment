package com.nice.homeassignment.messaginserver1.clients;

import com.nice.homeassignment.messaginserver1.configurations.ServersConfig;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Server2Client {

    private HttpClient httpClient;

    public Server2Client(ServersConfig serversConfig) {
        this.httpClient = new HttpClient(serversConfig.getServer2());
    }

    public void sendMessage(Message message) {
        Mono response = httpClient.post("/v1/messages", message, message.getClass());
        response.block();
    }
}
