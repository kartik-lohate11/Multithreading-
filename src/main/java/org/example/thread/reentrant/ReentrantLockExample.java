package org.example.thread.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();

    public void inner() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release the lock");
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        Runnable runnable = reentrantLockExample::inner;
        Thread t1 = new Thread(runnable,"T1");
        Thread t2 = new Thread(runnable,"T2");
        Thread t3 = new Thread(runnable,"T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
