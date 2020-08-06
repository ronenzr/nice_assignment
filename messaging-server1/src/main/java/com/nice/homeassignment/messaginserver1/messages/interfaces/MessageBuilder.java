package com.nice.homeassignment.messaginserver1.messages.interfaces;

public interface MessageBuilder<T> {
    boolean isValid(T value);
    Message build(T value) throws IllegalArgumentException;
}
