package com.codegym.creating;

public class CountdownByThreadClass extends Thread {
    private int count;

    public CountdownByThreadClass(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = count; i > 0; i--) {
            System.out.println("Countdown: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread bị gián đoạn!");
            }
        }
        System.out.println("Countdown hoàn thành!");
    }

    public static void main(String[] args) {
        CountdownByThreadClass countdown = new CountdownByThreadClass(10);
        countdown.start();
    }
}

