package ru.pas_zhukov.counter;

public interface SiteVisitCounter {

    /**
     * Увеличить счетчик посещения.
     */
    void incrementVisitCount();

    /**
     *
     * @return Текущее значение счётчика.
     */
    int getVisitCount();

    /**
     * Обнуляет счётчик.
     */
    void reset();
}
