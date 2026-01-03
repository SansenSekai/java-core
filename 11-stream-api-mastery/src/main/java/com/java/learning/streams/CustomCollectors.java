package com.java.learning.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Создание собственных Collector'ов.
 * 
 * Collector состоит из:
 * - supplier: создаёт контейнер для накопления
 * - accumulator: добавляет элемент в контейнер
 * - combiner: объединяет два контейнера (для параллельных стримов)
 * - finisher: преобразует контейнер в финальный результат
 * 
 * TODO: Реализуй все коллекторы
 */
public final class CustomCollectors {
    
    private CustomCollectors() {}
    
    /**
     * Коллектор для подсчёта среднего значения.
     * 
     * Возвращает OptionalDouble.empty() для пустого стрима.
     */
    public static Collector<Integer, ?, OptionalDouble> averaging() {
        // TODO: Реализуй
        // Подсказка: накапливай сумму и количество, затем вычисли среднее
        throw new UnsupportedOperationException("TODO: implement averaging");
    }
    
    /**
     * Коллектор для нахождения N максимальных элементов.
     * 
     * @param n количество элементов
     * @param comparator компаратор для сравнения
     */
    public static <T> Collector<T, ?, List<T>> topN(int n, Comparator<T> comparator) {
        // TODO: Реализуй
        // Подсказка: используй PriorityQueue
        throw new UnsupportedOperationException("TODO: implement topN");
    }
    
    /**
     * Коллектор для конкатенации строк с разделителем, префиксом и суффиксом.
     * 
     * Аналог Collectors.joining(), но написанный вручную.
     */
    public static Collector<String, ?, String> joining(String delimiter, String prefix, String suffix) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement joining");
    }
    
    /**
     * Коллектор для создания immutable списка.
     * 
     * В отличие от toList(), гарантированно возвращает неизменяемый список.
     */
    public static <T> Collector<T, ?, List<T>> toImmutableList() {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement toImmutableList");
    }
    
    /**
     * Коллектор для подсчёта статистики строк.
     * 
     * Возвращает объект со статистикой: count, totalLength, minLength, maxLength, avgLength.
     */
    public static Collector<String, ?, StringStats> stringStats() {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement stringStats");
    }
    
    /**
     * Статистика строк.
     */
    public record StringStats(
        long count,
        long totalLength,
        int minLength,
        int maxLength,
        double avgLength
    ) {
        public static StringStats EMPTY = new StringStats(0, 0, 0, 0, 0.0);
    }
}

