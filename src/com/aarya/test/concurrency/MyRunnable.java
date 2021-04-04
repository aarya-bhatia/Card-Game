package com.aarya.test.concurrency;

public class MyRunnable implements Runnable {

    private Counter c;
    private int id;
    private final int numIter;

    public MyRunnable(Counter c, int id, int numIter) {
        this.c = c;
        this.id = id;
        this.numIter = numIter;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIter; i++) {
            c.update(id);
        }
    }
}
