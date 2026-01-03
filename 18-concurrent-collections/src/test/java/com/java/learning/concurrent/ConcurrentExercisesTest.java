package com.java.learning.concurrent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Concurrent Exercises")
class ConcurrentExercisesTest {

    @Nested
    @DisplayName("EventCounter")
    class EventCounterTest {
        
        @Test
        @DisplayName("Подсчитывает события")
        void shouldCountEvents() {
            var counter = new ConcurrentExercises.EventCounter<String>();
            
            counter.increment("click");
            counter.increment("click");
            counter.increment("scroll");
            
            assertThat(counter.getCount("click")).isEqualTo(2);
            assertThat(counter.getCount("scroll")).isEqualTo(1);
            assertThat(counter.getCount("hover")).isEqualTo(0);
        }
        
        @Test
        @DisplayName("Потокобезопасен")
        @Timeout(10)
        void shouldBeThreadSafe() throws InterruptedException {
            var counter = new ConcurrentExercises.EventCounter<String>();
            int threads = 10;
            int incrementsPerThread = 10_000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            CountDownLatch latch = new CountDownLatch(threads);
            
            for (int i = 0; i < threads; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.increment("event");
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            assertThat(counter.getCount("event"))
                .isEqualTo((long) threads * incrementsPerThread);
        }
        
        @Test
        @DisplayName("getTotalCount возвращает сумму")
        void shouldReturnTotalCount() {
            var counter = new ConcurrentExercises.EventCounter<String>();
            
            counter.increment("a");
            counter.increment("a");
            counter.increment("b");
            
            assertThat(counter.getTotalCount()).isEqualTo(3);
        }
        
        @Test
        @DisplayName("getTopKey возвращает самый частый ключ")
        void shouldReturnTopKey() {
            var counter = new ConcurrentExercises.EventCounter<String>();
            
            counter.increment("a");
            counter.increment("b");
            counter.increment("b");
            counter.increment("c");
            counter.increment("c");
            counter.increment("c");
            
            assertThat(counter.getTopKey()).isEqualTo("c");
        }
    }
    
    @Nested
    @DisplayName("LoadingCache")
    class LoadingCacheTest {
        
        @Test
        @DisplayName("Загружает значение при первом доступе")
        void shouldLoadOnFirstAccess() {
            AtomicInteger loadCount = new AtomicInteger(0);
            
            var cache = new ConcurrentExercises.LoadingCache<String, Integer>(key -> {
                loadCount.incrementAndGet();
                return key.length();
            });
            
            assertThat(cache.get("hello")).isEqualTo(5);
            assertThat(loadCount.get()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("Возвращает кэшированное значение")
        void shouldReturnCachedValue() {
            AtomicInteger loadCount = new AtomicInteger(0);
            
            var cache = new ConcurrentExercises.LoadingCache<String, Integer>(key -> {
                loadCount.incrementAndGet();
                return key.length();
            });
            
            cache.get("test");
            cache.get("test");
            cache.get("test");
            
            assertThat(loadCount.get()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("Loader вызывается ровно один раз для ключа")
        @Timeout(10)
        void loaderShouldBeCalledOnce() throws InterruptedException {
            AtomicInteger loadCount = new AtomicInteger(0);
            
            var cache = new ConcurrentExercises.LoadingCache<String, Integer>(key -> {
                loadCount.incrementAndGet();
                try { Thread.sleep(10); } catch (InterruptedException e) {}
                return key.length();
            });
            
            int threads = 100;
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            CountDownLatch latch = new CountDownLatch(threads);
            
            for (int i = 0; i < threads; i++) {
                executor.submit(() -> {
                    cache.get("key");
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            assertThat(loadCount.get()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("invalidate удаляет значение")
        void shouldInvalidate() {
            AtomicInteger loadCount = new AtomicInteger(0);
            
            var cache = new ConcurrentExercises.LoadingCache<String, Integer>(key -> {
                loadCount.incrementAndGet();
                return key.length();
            });
            
            cache.get("test");
            cache.invalidate("test");
            cache.get("test");
            
            assertThat(loadCount.get()).isEqualTo(2);
        }
    }
}

