package org.example.thread.reentrant;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ThreadCommunication {
    private int data;
    private boolean isAvailable;

    public synchronized void producer(int data) {
        log.info("Producer Start");

        while (isAvailable) {
            try {
                log.info("Producer wait");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        log.info("Producer send data {}", data);
        this.data = data;
        isAvailable = true;
        notify();
    }

    public synchronized void consumer() {
        log.info("Consumer Start..");
        try {
            while (!isAvailable) {
                log.info("consumer wait for data");
                wait();
            }
            isAvailable = false;
            notify();
            log.info("Consume data = {}", this.data);

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadCommunication threadCommunication = new ThreadCommunication();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        threadCommunication.producer(i);
                    }
                }
        );

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadCommunication.consumer();
            }
        }
        );

        producer.start();
        consumer.start();
    }

}
