package com.example;

import com.example.task2.MultiThreadedFizzBuzz;

public class MultiThreadedFizzBuzzTest {
    public static void main(String[] args) {
        Thread multiThreadedFizzBuzz = new Thread(new MultiThreadedFizzBuzz(15));
        multiThreadedFizzBuzz.start();
    }
}
