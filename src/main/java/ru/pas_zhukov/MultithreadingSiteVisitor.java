package ru.pas_zhukov;

import ru.pas_zhukov.counter.SiteVisitCounter;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter counter;
    private final List<Thread> threads = new ArrayList<>();

    private Long startMillis;
    private Long endMillis;

    public MultithreadingSiteVisitor(SiteVisitCounter counter) {
        this.counter = counter;
    }

    /**
     * Запускает указанное количество потоков, каждый из которых
     * вызывает {@code incrementVisitCount()} на переданном объекте {@link ru.pas_zhukov.counter.SiteVisitCounter}.
     */
    public void visitMultithread(Integer count) {
        threads.clear();
        for (int i = 0; i < count; i++) {
            threads.add(new Thread(counter::incrementVisitCount));
        }
        startMillis = System.currentTimeMillis();
        threads.forEach(Thread::start);
    }

    /**
     * Ожидает завершения всех потоков.
     */
    public void waitUntilAllVisited() {
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        endMillis = System.currentTimeMillis();
    }

    /**
     *
     * @return Общее время обработки всех потоков в секундах.
     */
    public double getTotalTimeOfHandling() {
        return (double) (endMillis - startMillis) / 1000;
    }
}
