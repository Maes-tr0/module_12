package com.example;

import com.example.task1.TimeNotifier;

public class TimeNotifierTest {
    public static void main(String[] args) {
        Thread timeNotifier = new Thread(new TimeNotifier());
        timeNotifier.start();
    }
}
