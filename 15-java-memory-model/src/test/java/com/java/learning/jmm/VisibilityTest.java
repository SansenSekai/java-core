package com.java.learning.jmm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Java Memory Model - Visibility")
class VisibilityTest {

    /**
     * Этот тест демонстрирует, что WithVolatile работает корректно.
     * 
     * Благодаря volatile:
     * 1. Запись в flag видна другим потокам
     * 2. Happens-before гарантирует видимость value
     */
    @Test
    @DisplayName("volatile обеспечивает видимость")
    @Timeout(5)
    void volatileShouldEnsureVisibility() throws InterruptedException {
        var example = new VisibilityExamples.WithVolatile();
        AtomicBoolean readerSawValue = new AtomicBoolean(false);
        CountDownLatch done = new CountDownLatch(1);
        
        Thread reader = new Thread(() -> {
            while (!example.flag) {
                // spin
            }
            // Благодаря happens-before, value гарантированно 42
            if (example.value == 42) {
                readerSawValue.set(true);
            }
            done.countDown();
        });
        
        reader.start();
        Thread.sleep(50); // Даём reader время запуститься
        
        example.writer();
        
        done.await();
        assertThat(readerSawValue.get())
            .as("Reader should see value=42 thanks to happens-before")
            .isTrue();
    }
    
    /**
     * Тест для Double-Checked Locking.
     * 
     * Правильная реализация с volatile должна возвращать 
     * один и тот же экземпляр из всех потоков.
     */
    @Test
    @DisplayName("DCL с volatile потокобезопасен")
    @Timeout(10)
    void correctDCLShouldBeThreadSafe() throws InterruptedException {
        int threads = 100;
        Object[] instances = new Object[threads];
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threads);
        
        for (int i = 0; i < threads; i++) {
            int idx = i;
            new Thread(() -> {
                try {
                    start.await();
                    instances[idx] = VisibilityExamples.CorrectDCL.getInstance();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            }).start();
        }
        
        start.countDown(); // Все потоки стартуют одновременно
        done.await();
        
        // Все должны получить один и тот же экземпляр
        Object first = instances[0];
        for (Object instance : instances) {
            assertThat(instance).isSameAs(first);
        }
    }
}

