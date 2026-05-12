package org.example.thread.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    private Integer count = 0;

    public Integer getCount() {
        readLock.lock();
        try {
            return count;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public void incrementCount() {
        writeLock.lock();
        try {
            count++;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();

        Runnable read = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " count is = " + lock.getCount());
            }
        };

        Runnable write = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " write");
                lock.incrementCount();
            }
        };

        Thread t1 = new Thread(read);
        Thread t2 = new Thread(write);

        try {

            t2.start();
            Thread.sleep(50);
            t1.start();

            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Final Count = " + lock.getCount());
    }


}
