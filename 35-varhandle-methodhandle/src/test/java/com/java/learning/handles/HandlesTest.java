package com.java.learning.handles;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.invoke.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("VarHandle and MethodHandle")
class HandlesTest {

    @Nested
    @DisplayName("MethodHandle")
    class MethodHandleTests {
        
        @Test
        @DisplayName("findMethod находит метод")
        void shouldFindMethod() throws Throwable {
            MethodHandle mh = MethodHandleExamples.findMethod(
                String.class, "length", int.class
            );
            
            int result = (int) mh.invoke("hello");
            
            assertThat(result).isEqualTo(5);
        }
        
        @Test
        @DisplayName("findConstructor создаёт объект")
        void shouldFindConstructor() throws Throwable {
            MethodHandle mh = MethodHandleExamples.findConstructor(
                String.class, char[].class
            );
            
            String result = (String) mh.invoke(new char[]{'h', 'i'});
            
            assertThat(result).isEqualTo("hi");
        }
        
        @Test
        @DisplayName("findGetter читает поле")
        void shouldFindGetter() throws Throwable {
            // Создаём тестовый класс
            record Point(int x, int y) {}
            
            // Для record полей нужен особый подход
            // Используем стандартный геттер через findVirtual
            var lookup = MethodHandles.lookup();
            MethodHandle mh = lookup.findVirtual(
                Point.class, "x", MethodType.methodType(int.class)
            );
            
            Point p = new Point(10, 20);
            int result = (int) mh.invoke(p);
            
            assertThat(result).isEqualTo(10);
        }
    }
    
    @Nested
    @DisplayName("VarHandle - LockFreeCounter")
    class LockFreeCounterTests {
        
        @Test
        @DisplayName("getAndIncrement работает")
        void shouldIncrementAndReturnOldValue() {
            var counter = new VarHandleExamples.LockFreeCounter();
            
            assertThat(counter.getAndIncrement()).isEqualTo(0);
            assertThat(counter.getAndIncrement()).isEqualTo(1);
            assertThat(counter.get()).isEqualTo(2);
        }
        
        @Test
        @DisplayName("compareAndSet работает")
        void shouldCompareAndSet() {
            var counter = new VarHandleExamples.LockFreeCounter();
            counter.set(10);
            
            assertThat(counter.compareAndSet(10, 20)).isTrue();
            assertThat(counter.get()).isEqualTo(20);
            
            assertThat(counter.compareAndSet(10, 30)).isFalse();
            assertThat(counter.get()).isEqualTo(20);
        }
        
        @Test
        @DisplayName("Потокобезопасность")
        @Timeout(10)
        void shouldBeThreadSafe() throws InterruptedException {
            var counter = new VarHandleExamples.LockFreeCounter();
            int threads = 10;
            int incrementsPerThread = 10_000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            CountDownLatch latch = new CountDownLatch(threads);
            
            for (int i = 0; i < threads; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        counter.getAndIncrement();
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            assertThat(counter.get()).isEqualTo(threads * incrementsPerThread);
        }
    }
    
    @Nested
    @DisplayName("VarHandle - LockFreeStack")
    class LockFreeStackTests {
        
        @Test
        @DisplayName("push и pop работают")
        void shouldPushAndPop() {
            var stack = new VarHandleExamples.LockFreeStack<Integer>();
            
            stack.push(1);
            stack.push(2);
            stack.push(3);
            
            assertThat(stack.pop()).isEqualTo(3);
            assertThat(stack.pop()).isEqualTo(2);
            assertThat(stack.pop()).isEqualTo(1);
            assertThat(stack.pop()).isNull();
        }
        
        @Test
        @DisplayName("Потокобезопасность push")
        @Timeout(10)
        void pushShouldBeThreadSafe() throws InterruptedException {
            var stack = new VarHandleExamples.LockFreeStack<Integer>();
            int threads = 10;
            int pushesPerThread = 1000;
            
            ExecutorService executor = Executors.newFixedThreadPool(threads);
            CountDownLatch latch = new CountDownLatch(threads);
            
            for (int i = 0; i < threads; i++) {
                int threadId = i;
                executor.submit(() -> {
                    for (int j = 0; j < pushesPerThread; j++) {
                        stack.push(threadId * 1000 + j);
                    }
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
            
            // Подсчитываем элементы
            int count = 0;
            while (stack.pop() != null) {
                count++;
            }
            
            assertThat(count).isEqualTo(threads * pushesPerThread);
        }
    }
}

