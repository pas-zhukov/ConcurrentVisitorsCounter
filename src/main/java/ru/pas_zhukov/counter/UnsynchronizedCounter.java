package ru.pas_zhukov.counter;

public class UnsynchronizedCounter implements SiteVisitCounter {

    private int count = 0;

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