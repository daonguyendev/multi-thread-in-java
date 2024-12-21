package com.codegym.deadlock;

public class AvoidDeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> acquireLocks(lock1, lock2));
        Thread thread2 = new Thread(() -> acquireLocks(lock1, lock2));

        thread1.start();
        thread2.start();
    }

    private static void acquireLocks(Object lock1, Object lock2) {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + ": Locked lock1");

            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + ": Locked lock2");
            }
        }
    }
}

