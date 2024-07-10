package com.example.task2;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MultiThreadedFizzBuzz implements Runnable {
    private static int n;
    private static final BlockingDeque<String> queue = new LinkedBlockingDeque<>();

    public MultiThreadedFizzBuzz(int n) {
        MultiThreadedFizzBuzz.n = n;
    }

    @Override
    public void run() {
        new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start(); // fizz

        new Thread(() -> {
            try {
                buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start(); // buzz

        new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start(); // fizzbuzz

        new Thread(() -> {
            try {
                number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start(); // number
    }

    private static void fizz() throws InterruptedException{
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                queue.put("fizz");
            }
        }
    }

    private static void buzz() throws InterruptedException{
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                queue.put("buzz");
            }
        }
    }

    private static void fizzbuzz() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                queue.put("fizzbuzz");
            }
        }
    }

    private static void number() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.print(i);
            } else {
                System.out.print(queue.take());
            }
            if(i != n){
                System.out.print(" ");
            }
        }
    }
}
