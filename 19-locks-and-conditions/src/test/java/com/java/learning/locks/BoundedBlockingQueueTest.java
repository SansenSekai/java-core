package com.java.learning.locks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Bounded Blocking Queue")
class BoundedBlockingQueueTest {

    @Test
    @DisplayName("put и take работают корректно")
    @Timeout(5)
    void shouldPutAndTake() throws InterruptedException {
        var queue = new BoundedBlockingQueue<Integer>(3);
        
        queue.put(1);
        queue.put(2);
        queue.put(3);
        
        assertThat(queue.take()).isEqualTo(1);
        assertThat(queue.take()).isEqualTo(2);
        assertThat(queue.take()).isEqualTo(3);
    }
    
    @Test
    @DisplayName("take блокируется на пустой очереди")
    @Timeout(5)
    void takeShouldBlockOnEmpty() throws Exception {
        var queue = new BoundedBlockingQueue<Integer>(1);
        
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        Thread.sleep(100);
        assertThat(future.isDone()).isFalse();
        
        queue.put(42);
        assertThat(future.get(1, TimeUnit.SECONDS)).isEqualTo(42);
    }
    
    @Test
    @DisplayName("put блокируется на полной очереди")
    @Timeout(5)
    void putShouldBlockOnFull() throws Exception {
        var queue = new BoundedBlockingQueue<Integer>(1);
        queue.put(1);
        
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                queue.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        Thread.sleep(100);
        assertThat(future.isDone()).isFalse();
        
        queue.take();
        future.get(1, TimeUnit.SECONDS);
    }
    
    @Test
    @DisplayName("offer не блокируется")
    void offerShouldNotBlock() {
        var queue = new BoundedBlockingQueue<Integer>(1);
        
        assertThat(queue.offer(1)).isTrue();
        assertThat(queue.offer(2)).isFalse(); // Очередь полна
    }
    
    @Test
    @DisplayName("poll не блокируется")
    void pollShouldNotBlock() throws InterruptedException {
        var queue = new BoundedBlockingQueue<Integer>(1);
        
        assertThat(queue.poll()).isNull();
        
        queue.put(1);
        assertThat(queue.poll()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("Producer-Consumer: все элементы передаются")
    @Timeout(10)
    void producerConsumerShouldWork() throws InterruptedException {
        final int ITEMS = 10_000;
        var queue = new BoundedBlockingQueue<Integer>(10);
        AtomicInteger sum = new AtomicInteger(0);
        CountDownLatch done = new CountDownLatch(1);
        
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= ITEMS; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < ITEMS; i++) {
                try {
                    sum.addAndGet(queue.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            done.countDown();
        });
        
        producer.start();
        consumer.start();
        done.await();
        
        int expected = ITEMS * (ITEMS + 1) / 2;
        assertThat(sum.get()).isEqualTo(expected);
    }
}

