package com.nice.homeassignment.messaginserver1.messages.interfaces;

public interface Message<T> {
    void setOriginal(Object message);
    Object getOriginal();
    void setValue(T message);
    T getValue();
}
