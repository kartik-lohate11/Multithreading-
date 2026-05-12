package org.example.thread.executor;

import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Start processing..");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "Done";
                }
        ,executor);


        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(
                () -> {
                    return 1;
                }
        );

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(
                () -> {
                    return 3;
                }
        );

        System.out.println(completableFuture2.thenCombine(completableFuture1, Integer::sum).get());


        System.out.println(completableFuture.join());
        System.out.println("Main Closed.");
        executor.shutdown();

    }
}
