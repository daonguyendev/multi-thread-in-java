package com.codegym.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockWithReentrantLock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("Thread 1: Locked lock1");
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                lock2.lock();
                try {
                    System.out.println("Thread 1: Locked lock2");
                } finally {
                    lock2.unlock();
                }
            } finally {
                lock1.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            try {
                System.out.println("Thread 2: Locked lock2");
                try { Thread.sleep(50); } catch (InterruptedException e) {}

                lock1.lock();
                try {
                    System.out.println("Thread 2: Locked lock1");
                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}

