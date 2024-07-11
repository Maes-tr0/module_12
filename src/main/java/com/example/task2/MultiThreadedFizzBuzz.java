package com.example.task2;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedFizzBuzz implements Runnable {
    private final static Object MONITOR_LOCKER = new Object();
    private static int n;
    private final AtomicInteger current = new AtomicInteger(1);
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

    private void fizz() throws InterruptedException {
        while (true) {
            synchronized (MONITOR_LOCKER) {
                while (current.get() <= n && (current.get() % 3 != 0 || current.get() % 5 == 0)) {
                    MONITOR_LOCKER.wait();
                }
                if (current.get() > n) break;
                queue.put("fizz");
                current.incrementAndGet();
                MONITOR_LOCKER.notifyAll();
            }
        }
    }

    private void buzz() throws InterruptedException {
        while (true) {
            synchronized (MONITOR_LOCKER) {
                while (current.get() <= n && (current.get() % 5 != 0 || current.get() % 3 == 0)) {
                    MONITOR_LOCKER.wait();
                }
                if (current.get() > n) break;
                queue.put("buzz");
                current.incrementAndGet();
                MONITOR_LOCKER.notifyAll();
            }
        }
    }

    private void fizzbuzz() throws InterruptedException {
        while (true) {
            synchronized (MONITOR_LOCKER) {
                while (current.get() <= n && (current.get() % 3 != 0 || current.get() % 5 != 0)) {
                    MONITOR_LOCKER.wait();
                }
                if (current.get() > n) break;
                queue.put("fizzbuzz");
                current.incrementAndGet();
                MONITOR_LOCKER.notifyAll();
            }
        }
    }

    private void number() throws InterruptedException {
        while (true) {
            synchronized (MONITOR_LOCKER) {
                while (current.get() <= n && (current.get() % 3 == 0 || current.get() % 5 == 0)) {
                    MONITOR_LOCKER.wait();
                }
                if (current.get() > n) break;
                queue.put(String.valueOf(current.get()));
                current.incrementAndGet();
                MONITOR_LOCKER.notifyAll();
            }
        }

        while (!queue.isEmpty()) {
            System.out.print(queue.take() + " ");
        }
    }
}
