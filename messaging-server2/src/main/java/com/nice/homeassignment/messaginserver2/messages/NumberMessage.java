package com.nice.homeassignment.messaginserver2.messages;

import com.nice.homeassignment.messaginserver2.messages.interfaces.Message;


public class NumberMessage implements Message<Float> {

    private Object original;
    private Float value;


    @Override
    public void setOriginal(Object message) {
        this.original = message;
    }

    @Override
    public Object getOriginal() {
        return this.original;
    }

    @Override
    public void setValue(Float message) {
        this.value = message;
    }

    @Override
    public Float getValue() {
        return this.value;
    }
}
