package com.nice.homeassignment.messaginserver1.queues;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Objects;

public class Queue<T> {

    private String name;
    private LinkedList<T> innerQueue;

    public Queue(String name) {
        this.name = StringUtils.lowerCase(name);
        this.innerQueue = new LinkedList<T>();
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return innerQueue.isEmpty();
    }

    public void add(T item) {
        innerQueue.addLast(item);
    }

    public T peek() {
        return innerQueue.peekFirst();
    }

    public T get() {
        return innerQueue.removeFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue<?> queue = (Queue<?>) o;
        return Objects.equals(name, queue.name) &&
                Objects.equals(innerQueue, queue.innerQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, innerQueue);
    }

    @Override
    public String toString() {
        return "Queue{" +
                "name='" + name + '\'' +
                ", innerQueue=" + innerQueue +
                '}';
    }
}