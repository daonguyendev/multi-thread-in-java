package com.codegym.creating;

public class CountdownByIRunnable implements Runnable {
    private int start;

    public CountdownByIRunnable(int start) {
        this.start = start;
    }

    @Override
    public void run() {
        for (int i = start; i >= 0; i--) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Countdown interrupted!");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + ": Countdown finished!");
    }

    public static void main(String[] args) {
        int countdownFrom = 10;

        // Tạo và khởi chạy 2 countdown đồng thời
        Thread thread1 = new Thread(new CountdownByIRunnable(countdownFrom), "Thread 1");
        Thread thread2 = new Thread(new CountdownByIRunnable(countdownFrom / 2), "Thread 2");

        thread1.start();
        thread2.start();
    }
}

