package com.nice.homeassignment.messaginserver1.factories;

import com.nice.homeassignment.messaginserver1.factories.interfaces.Factory;
import com.nice.homeassignment.messaginserver1.messages.interfaces.Message;
import com.nice.homeassignment.messaginserver1.messages.interfaces.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MessageFactory implements Factory<Message, String> {

    private Set<MessageBuilder<String>> messageBuilders;

    public MessageFactory(Set<MessageBuilder<String>> messageBuilders) {
        this.messageBuilders = messageBuilders;
    }

    @Override
    public Message get(String type) throws IllegalArgumentException {
        return this.messageBuilders.stream()
                                    .filter(builder -> builder.isValid(type))
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalArgumentException(String.format("[MessageFactory] Input cannot be parsed: %s", type)))
                                    .build(type);
    }
}
