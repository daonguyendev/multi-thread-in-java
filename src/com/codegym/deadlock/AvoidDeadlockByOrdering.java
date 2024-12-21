package com.codegym.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class AvoidDeadlockByOrdering {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> acquireLocks(lock1, lock2));
        Thread thread2 = new Thread(() -> acquireLocks(lock1, lock2));

        thread1.start();
        thread2.start();
    }

    private static void acquireLocks(ReentrantLock lock1, ReentrantLock lock2) {
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": Locked lock1");

            lock2.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ": Locked lock2");
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
    }
}

