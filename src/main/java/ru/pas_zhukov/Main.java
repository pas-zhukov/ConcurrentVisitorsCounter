package ru.pas_zhukov;

import ru.pas_zhukov.counter.*;

public class Main {
    public static void main(String[] args) {
        testCounter(new UnsynchronizedCounter());
        testCounter(new VolatileCounter());
        testCounter(new SynchronizedBlockCounter());
        testCounter(new AtomicIntegerCounter());
        testCounter(new ReentrantLockCounter());
    }

    private static void testCounter(SiteVisitCounter counter) {
        System.out.println("Testing: " + counter.getClass().getSimpleName());

        MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);

        // Test with 10 threads
        System.out.println("Running with 10 threads...");
        visitor.visitMultithread(10);
        visitor.waitUntilAllVisited();
        double timeTaken = visitor.getTotalTimeOfHandling();
        System.out.printf("Time: %.2f seconds, Count: %d%n", timeTaken, counter.getVisitCount());

        // Reset counter for next test
        counter.reset();

        // Test with 100 threads
        System.out.println("Running with 100 threads...");
        visitor.visitMultithread(100);
        visitor.waitUntilAllVisited();
        timeTaken = visitor.getTotalTimeOfHandling();
        System.out.printf("Time: %.2f seconds, Count: %d%n", timeTaken, counter.getVisitCount());

        System.out.println();
    }
}
