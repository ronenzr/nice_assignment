package com.nice.homeassignment.messaginserver1.factories.interfaces;

public interface Factory<T, P> {
    T get(P type) throws IllegalArgumentException;
}
