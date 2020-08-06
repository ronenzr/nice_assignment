package com.nice.homeassignment.messaginserver1.services;

import com.nice.homeassignment.messaginserver1.listeners.interfaces.Listener;
import com.nice.homeassignment.messaginserver1.queues.Queue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueuesService {

    private static final Logger log = LoggerFactory.getLogger(QueuesService.class);


    private Set<Queue> queues;
    private Map<String, Queue> queuesByNames;
    private Map<Queue, Listener> queueListener;

    public QueuesService() {
        this.queues = new HashSet<>();
        this.queueListener = new HashMap<>();
        this.queuesByNames = new HashMap<>();
    }

    public void addQueue(Queue toAdd) {
        if(toAdd == null || StringUtils.isEmpty(toAdd.getName())) {
            log.warn("[QueuesService] Queue added is not valid: {}", toAdd);
            throw new IllegalArgumentException("[QueuesService] Queue added is not valid");
        }
        this.queues.add(toAdd);
        this.queuesByNames.put(toAdd.getName(), toAdd);
    }

    public void addListener(String queueName, Listener listener) {
        Queue queue = getQueue(queueName);
        queueListener.put(queue, listener);
    }


    public void add(String queueName, Object item) {
        Queue queue = getQueue(queueName);
        queue.add(item);
    }

    @Scheduled(fixedRate = 100)
    public void queuesIteration() {
        queueListener.entrySet().parallelStream()
                                .filter(entry -> !entry.getValue().isProcessing() && !entry.getKey().isEmpty())
                                .forEach(entry -> tryHandleQueueItem(entry.getKey(), entry.getValue()));
    }


    //--

    private Queue getQueue(String queueName){
        String lowerCaseQueueName = StringUtils.lowerCase(queueName);
        if(queuesByNames.containsKey(lowerCaseQueueName)) {
            return queuesByNames.get(lowerCaseQueueName);
        }

        throw new IllegalArgumentException(String.format("[QueuesService] Queue with specified name could not be found: %s", queueName));
    }

    private void tryHandleQueueItem(Queue queue, Listener listener){
        try {
            listener.handle(queue.peek());
            queue.get();
        } catch (Exception ex){
            log.error("[QueuesService] Failed to process queue item, for queue: {}", queue.getName(), ex);
        }
    }
}
