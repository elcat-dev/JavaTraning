package com.flamexander.multithreading.p5_executor_service;

public class InterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    System.out.println(1);
                }
            }
        });
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}
