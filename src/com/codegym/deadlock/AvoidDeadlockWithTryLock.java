package com.codegym.deadlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class AvoidDeadlockWithTryLock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> tryLockExample(lock1, lock2));
        Thread thread2 = new Thread(() -> tryLockExample(lock2, lock1));

        thread1.start();
        thread2.start();
    }

    private static void tryLockExample(ReentrantLock firstLock, ReentrantLock secondLock) {
        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        try {
            // Cố gắng lấy lock thứ nhất trong 1 giây
            lock1Acquired = firstLock.tryLock(1, TimeUnit.SECONDS);
            if (lock1Acquired) {
                System.out.println(Thread.currentThread().getName() + ": Locked firstLock");

                // Giả lập thời gian xử lý
                Thread.sleep(50);

                // Cố gắng lấy lock thứ hai trong 1 giây
                lock2Acquired = secondLock.tryLock(1, TimeUnit.SECONDS);
                if (lock2Acquired) {
                    System.out.println(Thread.currentThread().getName() + ": Locked secondLock");
                } else {
                    System.out.println(Thread.currentThread().getName() + ": Could not lock secondLock, releasing firstLock...");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + ": Could not lock firstLock, retrying...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Giải phóng lock nếu đã lấy được
            if (lock1Acquired) {
                firstLock.unlock();
                System.out.println(Thread.currentThread().getName() + ": Released firstLock");
            }
            if (lock2Acquired) {
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + ": Released secondLock");
            }
        }
    }
}

