package com.example.task2;

import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedFizzBuzz implements Runnable {
    private final int n;
    private final static ScheduledExecutorService SES = new ScheduledThreadPoolExecutor(1);

    public MultiThreadedFizzBuzz(int n) {
        this.n = n;
    }

    @Override
    public void run() {

    }

}
