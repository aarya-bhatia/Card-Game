package com.aarya.test.concurrency;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiThreadingLesson {

    @Test
    public void updateCounterTest() {
        Counter counter = new Counter();
        int numIter = 5;

        Thread thread1 = new Thread(new MyRunnable(counter, 1, numIter));
        Thread thread2 = new Thread(new MyRunnable(counter, 2, numIter));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception: " + ex.getMessage());
        }

        assertEquals("Final value of counter should be right", counter.getCount(), 2 * numIter);
    }
}

