package ru.pas_zhukov.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return count.get();
    }

    @Override
    public void reset() {
        count.set(0);
    }
}
