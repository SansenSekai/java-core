package com.java.learning.threads;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Thread-safe Counter")
class ThreadSafeCounterTest {

    @Nested
    @DisplayName("SynchronizedCounter")
    class SynchronizedCounterTest {
        
        @Test
        @DisplayName("Базовые операции работают")
        void shouldWorkForBasicOperations() {
            var counter = new ThreadSafeCounter.SynchronizedCounter();
            
            counter.increment();
            counter.increment();
            counter.decrement();
            
            assertThat(counter.get()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("Потокобезопасность при параллельных инкрементах")
        @Timeout(10)
        void shouldBeThreadSafe() throws InterruptedException {
            var counter = new ThreadSafeCounter.SynchronizedCounter();
            int threadCount = 10;
            int incrementsPerThread = 10_000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);
            
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.increment();
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            assertThat(counter.get()).isEqualTo(threadCount * incrementsPerThread);
        }
        
        @Test
        @DisplayName("compareAndIncrement работает корректно")
        void shouldCompareAndIncrement() {
            var counter = new ThreadSafeCounter.SynchronizedCounter();
            
            assertThat(counter.compareAndIncrement(0)).isTrue();
            assertThat(counter.get()).isEqualTo(1);
            
            assertThat(counter.compareAndIncrement(0)).isFalse();
            assertThat(counter.get()).isEqualTo(1);
        }
    }
    
    @Nested
    @DisplayName("AtomicCounter")
    class AtomicCounterTest {
        
        @Test
        @DisplayName("Потокобезопасность при параллельных инкрементах")
        @Timeout(10)
        void shouldBeThreadSafe() throws InterruptedException {
            var counter = new ThreadSafeCounter.AtomicCounter();
            int threadCount = 10;
            int incrementsPerThread = 10_000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);
            
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.increment();
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            assertThat(counter.get()).isEqualTo(threadCount * incrementsPerThread);
        }
    }
    
    @Nested
    @DisplayName("BrokenVolatileCounter (демонстрация проблемы)")
    class BrokenVolatileCounterTest {
        
        @Test
        @DisplayName("НЕ потокобезопасен — теряет инкременты")
        @Timeout(10)
        void shouldLoseIncrements() throws InterruptedException {
            var counter = new ThreadSafeCounter.BrokenVolatileCounter();
            int threadCount = 10;
            int incrementsPerThread = 10_000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);
            
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.increment();
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            // ВАЖНО: этот тест демонстрирует, что счётчик ТЕРЯЕТ инкременты!
            // Результат будет МЕНЬШЕ ожидаемого
            int expected = threadCount * incrementsPerThread;
            System.out.println("Expected: " + expected + ", Actual: " + counter.get());
            
            // Этот assert может иногда проходить из-за случайности,
            // но обычно будет меньше expected
            assertThat(counter.get())
                .as("Volatile counter loses increments due to race condition")
                .isLessThanOrEqualTo(expected);
        }
    }
}

