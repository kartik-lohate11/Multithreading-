package org.example.thread;

public class ThreadState extends Thread {

    public void run() {
        try {
            System.out.println("RUNNING");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // NEW State (When Thread Created)
        ThreadState t1 = new ThreadState();
        System.out.println(t1.getState());

        t1.start(); // IN RUNNABLE STATE (WHEN THREAD IS READY TO EXECUTE (READY TO GET CPU))
        System.out.println(t1.getState());

        Thread.sleep(1000); // TIME WAITING STATE WAIT FOR 1SEC
        System.out.println(t1.getState());

        t1.join(); // MAIN THREAD WAIT UNTIL T1 IS FINISHED
        System.out.println(t1.getState());

    }

}
