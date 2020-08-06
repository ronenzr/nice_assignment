package com.nice.homeassignment.messaginserver1.listeners.interfaces;

public interface Listener<T> {
    boolean isProcessing();
    void handle(T message);
}
