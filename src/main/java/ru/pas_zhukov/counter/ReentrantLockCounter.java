package ru.pas_zhukov.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    private final Lock lock = new ReentrantLock();

    private int count = 0;

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        return count;
    }

    @Override
    public void reset() {
        count = 0;
    }
}
