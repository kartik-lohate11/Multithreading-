package org.example.thread.executor;

import java.util.concurrent.*;

public class ExecutorExample {


    public void print(int i) {
        try {
            System.out.println(i + " Processing...");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorExample executorExample = new ExecutorExample();
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Runnable run = () -> {
                executorExample.print(finalI);
            };
            Future<?> f = executorService.submit(run);
            System.out.println("I am future");
        }

        executorService.shutdown();

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Total Time = " + (System.currentTimeMillis() - start));

    }
}
