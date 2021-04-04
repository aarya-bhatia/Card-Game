package com.aarya.test.concurrency;

public class Counter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void update(int id) {
        setCount(getCount() + 1);
    }
}
