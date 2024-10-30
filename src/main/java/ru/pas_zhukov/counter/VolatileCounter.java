package ru.pas_zhukov.counter;

public class VolatileCounter implements SiteVisitCounter {

    private volatile int count;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
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
