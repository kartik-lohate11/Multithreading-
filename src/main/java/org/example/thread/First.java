package org.example.thread;

public class First extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
