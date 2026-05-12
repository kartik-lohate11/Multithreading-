package org.example;


import org.example.thread.First;
import org.example.thread.Second;


public class App {
    public static void main(String[] args) {
        First first = new First();
        first.start();

        Second second = new Second();
        Thread t2 = new Thread(second);
        t2.start();

        Thread t3 = new Thread(
                () -> {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        );
        t3.start();

        while (true)
            System.out.println(Thread.currentThread().getName());

    }
}
