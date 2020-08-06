package com.nice.homeassignment.messaginserver1.services.interfaces;

public interface Handler<T> {
    void process(T value);
}
