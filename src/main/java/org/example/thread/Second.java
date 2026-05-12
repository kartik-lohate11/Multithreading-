package org.example.thread;

public class Second implements Runnable{
    @Override
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
