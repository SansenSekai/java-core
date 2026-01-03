package com.java.learning.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/**
 * Практика работы с concurrent коллекциями.
 * 
 * ConcurrentHashMap — основная concurrent коллекция:
 * - compute/computeIfAbsent/computeIfPresent — атомарные операции
 * - merge — атомарное слияние
 * - forEach/reduce — параллельные операции
 * 
 * TODO: Реализуй все классы
 */
public class ConcurrentExercises {
    
    /**
     * Потокобезопасный счётчик событий.
     * 
     * Подсчитывает количество вхождений каждого ключа.
     */
    public static class EventCounter<K> {
        
        private final ConcurrentHashMap<K, AtomicLong> counters = new ConcurrentHashMap<>();
        
        /**
         * Увеличивает счётчик для ключа.
         */
        public void increment(K key) {
            // TODO: Реализуй
            // Подсказка: computeIfAbsent + AtomicLong.incrementAndGet
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Возвращает текущее значение счётчика.
         */
        public long getCount(K key) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Возвращает общую сумму всех счётчиков.
         */
        public long getTotalCount() {
            // TODO: Реализуй
            // Подсказка: reduceValuesToLong
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Возвращает ключ с максимальным счётчиком.
         */
        public K getTopKey() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Потокобезопасный кэш с lazy loading.
     * 
     * Значения загружаются только при первом доступе.
     * Гарантируется, что loader вызывается только один раз для каждого ключа.
     */
    public static class LoadingCache<K, V> {
        
        private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();
        private final Function<K, V> loader;
        
        public LoadingCache(Function<K, V> loader) {
            this.loader = loader;
        }
        
        /**
         * Получает значение из кэша или загружает его.
         * 
         * Важно: loader должен вызываться ровно один раз для каждого ключа,
         * даже при одновременном доступе из нескольких потоков.
         */
        public V get(K key) {
            // TODO: Реализуй
            // Подсказка: computeIfAbsent
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Инвалидирует запись.
         */
        public void invalidate(K key) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Возвращает размер кэша.
         */
        public int size() {
            return cache.size();
        }
    }
    
    /**
     * Потокобезопасный Rate Limiter.
     * 
     * Ограничивает количество операций в единицу времени.
     */
    public static class RateLimiter {
        
        private final int maxRequests;
        private final long windowMillis;
        private final ConcurrentHashMap<String, java.util.concurrent.atomic.LongAdder> counters 
            = new ConcurrentHashMap<>();
        private volatile long windowStart;
        
        public RateLimiter(int maxRequests, java.time.Duration window) {
            this.maxRequests = maxRequests;
            this.windowMillis = window.toMillis();
            this.windowStart = System.currentTimeMillis();
        }
        
        /**
         * Проверяет, разрешён ли запрос для данного ключа.
         * 
         * @return true если запрос разрешён
         */
        public boolean tryAcquire(String key) {
            // TODO: Реализуй
            // Подсказка: проверь временное окно, обнови счётчик
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

