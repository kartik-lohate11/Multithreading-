package org.example.thread;

import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadMethods implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) System.out.println("RUNNING");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] arr) {

        Thread t1 = new Thread(
                new ThreadMethods()
        );

        t1.start();
        System.out.println(t1.getName());
        t1.interrupt();
    }
}
