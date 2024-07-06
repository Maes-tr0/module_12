package com.example.task1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeNotifier implements Runnable {
    private static final ScheduledExecutorService SES = new ScheduledThreadPoolExecutor(1);
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        Runnable sayTime = () -> System.out.println(counter.incrementAndGet() + " seconds");

        Runnable say5SecondsLeft = () -> {
            if (counter.get() % 5 == 0) {
                System.out.println("5 seconds passed!!!");
            }
        };

        SES.scheduleAtFixedRate(sayTime, 1, 1, TimeUnit.SECONDS);
        SES.scheduleAtFixedRate(say5SecondsLeft, 5, 5, TimeUnit.SECONDS);
    }
}
