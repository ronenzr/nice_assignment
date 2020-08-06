package com.nice.homeassignment.messaginserver2.services;

import com.nice.homeassignment.messaginserver2.adapters.interfaces.OutputAdapter;
import com.nice.homeassignment.messaginserver2.messages.NumberMessage;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessagesService {

    private Float sum;
    private Set<OutputAdapter> outputAdapters;

    public MessagesService(Set<OutputAdapter> outputAdapters) {
        this.outputAdapters = outputAdapters;
        this.sum = 0F;
    }

    public void handleMessage(NumberMessage message) {
        Float value = message.getValue();
        sum += value;
        outputAdapters.stream().forEach(adapter -> printMessage(adapter, message));
    }

    private void printMessage(OutputAdapter adapter, NumberMessage message) {
        adapter.print("message value: " + message.getValue() + "\n");
        adapter.print("original message: " + message.getOriginal() + "\n");
        adapter.print("sum: " + sum + "\n");
    }
}
