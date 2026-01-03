package com.java.learning.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dynamic Proxies")
class ProxyTest {

    // Тестовый интерфейс
    interface Calculator {
        int add(int a, int b);
        int multiply(int a, int b);
        int divide(int a, int b);
    }
    
    // Тестовая реализация
    static class SimpleCalculator implements Calculator {
        @Override
        public int add(int a, int b) { return a + b; }
        
        @Override
        public int multiply(int a, int b) { return a * b; }
        
        @Override
        public int divide(int a, int b) { return a / b; }
    }
    
    @Nested
    @DisplayName("LoggingProxy")
    class LoggingProxyTest {
        
        @Test
        @DisplayName("Логирует вызовы методов")
        void shouldLogMethodCalls() {
            Calculator calc = new SimpleCalculator();
            List<String> logs = new ArrayList<>();
            
            Calculator proxy = LoggingProxy.create(calc, Calculator.class, logs::add);
            
            proxy.add(2, 3);
            
            assertThat(logs).anyMatch(log -> log.contains("add"));
            assertThat(logs).anyMatch(log -> log.contains("5") || log.contains("Returned"));
        }
        
        @Test
        @DisplayName("Логирует исключения")
        void shouldLogExceptions() {
            Calculator calc = new SimpleCalculator();
            List<String> logs = new ArrayList<>();
            
            Calculator proxy = LoggingProxy.create(calc, Calculator.class, logs::add);
            
            assertThatThrownBy(() -> proxy.divide(1, 0))
                .isInstanceOf(ArithmeticException.class);
            
            assertThat(logs).anyMatch(log -> 
                log.contains("Threw") || log.contains("Exception"));
        }
    }
    
    @Nested
    @DisplayName("CachingProxy")
    class CachingProxyTest {
        
        @Test
        @DisplayName("Кэширует результаты")
        void shouldCacheResults() {
            AtomicInteger callCount = new AtomicInteger(0);
            
            Calculator calc = new Calculator() {
                @Override
                public int add(int a, int b) {
                    callCount.incrementAndGet();
                    return a + b;
                }
                
                @Override
                public int multiply(int a, int b) {
                    callCount.incrementAndGet();
                    return a * b;
                }
                
                @Override
                public int divide(int a, int b) {
                    callCount.incrementAndGet();
                    return a / b;
                }
            };
            
            Calculator proxy = CachingProxy.create(calc, Calculator.class);
            
            // Первый вызов
            assertThat(proxy.add(2, 3)).isEqualTo(5);
            assertThat(callCount.get()).isEqualTo(1);
            
            // Повторный вызов с теми же аргументами — из кэша
            assertThat(proxy.add(2, 3)).isEqualTo(5);
            assertThat(callCount.get()).isEqualTo(1); // Не увеличился!
            
            // Другие аргументы — новый вызов
            assertThat(proxy.add(3, 4)).isEqualTo(7);
            assertThat(callCount.get()).isEqualTo(2);
        }
    }
    
    @Nested
    @DisplayName("RetryProxy")
    class RetryProxyTest {
        
        @Test
        @DisplayName("Повторяет при исключении")
        void shouldRetryOnException() {
            AtomicInteger attempts = new AtomicInteger(0);
            
            Calculator calc = new Calculator() {
                @Override
                public int add(int a, int b) {
                    if (attempts.incrementAndGet() < 3) {
                        throw new RuntimeException("Temporary failure");
                    }
                    return a + b;
                }
                
                @Override
                public int multiply(int a, int b) { return a * b; }
                
                @Override
                public int divide(int a, int b) { return a / b; }
            };
            
            Calculator proxy = RetryProxy.create(
                calc, Calculator.class, 5, RuntimeException.class
            );
            
            int result = proxy.add(2, 3);
            
            assertThat(result).isEqualTo(5);
            assertThat(attempts.get()).isEqualTo(3);
        }
        
        @Test
        @DisplayName("Выбрасывает исключение после всех попыток")
        void shouldThrowAfterMaxRetries() {
            Calculator calc = new Calculator() {
                @Override
                public int add(int a, int b) {
                    throw new RuntimeException("Always fails");
                }
                
                @Override
                public int multiply(int a, int b) { return a * b; }
                
                @Override
                public int divide(int a, int b) { return a / b; }
            };
            
            Calculator proxy = RetryProxy.create(
                calc, Calculator.class, 3, RuntimeException.class
            );
            
            assertThatThrownBy(() -> proxy.add(2, 3))
                .isInstanceOf(RuntimeException.class);
        }
    }
}

