package com.nice.homeassignment.messaginserver2.adapters.output;

import com.nice.homeassignment.messaginserver2.adapters.interfaces.OutputAdapter;
import org.springframework.stereotype.Component;

@Component
public class ConsoleAdapter implements OutputAdapter {

    @Override
    public void print(Object message) {
        System.out.print(message);
    }
}
