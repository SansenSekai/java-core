package com.java.learning.sync;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Bounded Buffer")
class BoundedBufferTest {

    @Test
    @DisplayName("Базовый put/take работает")
    @Timeout(5)
    void shouldPutAndTake() throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        
        buffer.put(1);
        buffer.put(2);
        
        assertThat(buffer.take()).isEqualTo(1);
        assertThat(buffer.take()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("take блокируется на пустом буфере")
    @Timeout(5)
    void takeShouldBlockOnEmpty() throws Exception {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(1);
        
        // Запускаем take в отдельном потоке
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                return buffer.take(); // Должен заблокироваться
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        // Даём время заблокироваться
        Thread.sleep(100);
        assertThat(future.isDone()).isFalse();
        
        // Добавляем элемент — take должен разблокироваться
        buffer.put(42);
        assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(42);
    }
    
    @Test
    @DisplayName("put блокируется на полном буфере")
    @Timeout(5)
    void putShouldBlockOnFull() throws Exception {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(1);
        buffer.put(1); // Буфер полон
        
        // Запускаем put в отдельном потоке
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                buffer.put(2); // Должен заблокироваться
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        Thread.sleep(100);
        assertThat(future.isDone()).isFalse();
        
        // Забираем элемент — put должен разблокироваться
        buffer.take();
        future.get(1, TimeUnit.SECONDS);
    }
    
    @Test
    @DisplayName("Producer-Consumer: все элементы передаются корректно")
    @Timeout(10)
    void shouldHandleProducerConsumer() throws InterruptedException {
        final int ITEM_COUNT = 10000;
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        AtomicInteger sum = new AtomicInteger(0);
        CountDownLatch done = new CountDownLatch(1);
        
        // Producer
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= ITEM_COUNT; i++) {
                try {
                    buffer.put(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        // Consumer
        Thread consumer = new Thread(() -> {
            int count = 0;
            while (count < ITEM_COUNT) {
                try {
                    sum.addAndGet(buffer.take());
                    count++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            done.countDown();
        });
        
        producer.start();
        consumer.start();
        
        done.await(10, TimeUnit.SECONDS);
        
        // Сумма 1 + 2 + ... + ITEM_COUNT
        int expectedSum = ITEM_COUNT * (ITEM_COUNT + 1) / 2;
        assertThat(sum.get()).isEqualTo(expectedSum);
    }
}

