package org.example.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        Integer threadPoolSize = 3;
        ExecutorService service = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch countDownLatch = new CountDownLatch(threadPoolSize);

        service.submit(new Work(1, countDownLatch));
        service.submit(new Work(2, countDownLatch));
        service.submit(new Work(3, countDownLatch));

        countDownLatch.await();

        service.shutdown();
        System.out.println("Processing Completed");

    }

    static class Work implements Callable<String> {

        private final int count;
        private final CountDownLatch countDownLatch;

        public Work(int count, CountDownLatch countDownLatch) {
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            System.out.println(count + " is processing...");
            countDownLatch.countDown();
            return "Done";
        }
    }
}
