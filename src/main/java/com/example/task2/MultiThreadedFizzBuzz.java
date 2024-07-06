package com.example.task2;

import java.util.*;

public class MultiThreadedFizzBuzz implements Runnable {
    private final int n;
    private int current = 1;
    private final LinkedList<String> linkedList = new LinkedList<>();

    public MultiThreadedFizzBuzz(int n) {
        this.n = n;
    }

    private synchronized void fizz() throws InterruptedException {
        while (current <= n) {
            while (current <= n && (current % 3 != 0 || current % 5 == 0)) {
                wait();
            }
            if (current <= n) {
                linkedList.add("fizz");
                current++;
                notifyAll();
            }
        }
    }

    private synchronized void buzz() throws InterruptedException {
        while (current <= n) {
            while (current <= n && (current % 5 != 0 || current % 3 == 0)) {
                wait();
            }
            if (current <= n) {
                linkedList.add("buzz");
                current++;
                notifyAll();
            }
        }
    }

    private synchronized void fizzbuzz() throws InterruptedException {
        while (current <= n) {
            while (current <= n && current % 15 != 0) {
                wait();
            }
            if (current <= n) {
                linkedList.add("fizzbuzz");
                current++;
                notifyAll();
            }
        }
    }

    private synchronized void number() throws InterruptedException {
        while (current <= n) {
            while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                wait();
            }
            if (current <= n) {
                linkedList.add(String.valueOf(current));
                current++;
                notifyAll();
            }
        }
    }

    private synchronized void print(){
        linkedList.forEach(elem -> System.out.print(elem + " "));
    }

    public synchronized void start() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                buzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                number();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();

        print();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
