package com.codegym.deadlock;

public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Locked lock1");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (lock2) {
                    System.out.println("Thread 1: Locked lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Locked lock2");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (lock1) {
                    System.out.println("Thread 2: Locked lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

