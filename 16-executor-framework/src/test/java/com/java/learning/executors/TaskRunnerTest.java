package com.java.learning.executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Task Runner")
class TaskRunnerTest {

    @Nested
    @DisplayName("executeAll")
    class ExecuteAll {
        
        @Test
        @DisplayName("Выполняет все задачи параллельно")
        @Timeout(10)
        void shouldExecuteAllTasks() throws Exception {
            List<Callable<Integer>> tasks = List.of(
                () -> { Thread.sleep(100); return 1; },
                () -> { Thread.sleep(100); return 2; },
                () -> { Thread.sleep(100); return 3; }
            );
            
            List<Integer> results = TaskRunner.executeAll(tasks, 3, Duration.ofSeconds(5));
            
            assertThat(results).containsExactly(1, 2, 3);
        }
        
        @Test
        @DisplayName("Выбрасывает TimeoutException при превышении")
        @Timeout(5)
        void shouldTimeoutIfTooSlow() {
            List<Callable<Integer>> tasks = List.of(
                () -> { Thread.sleep(10000); return 1; }
            );
            
            assertThatThrownBy(() -> 
                TaskRunner.executeAll(tasks, 1, Duration.ofMillis(100))
            ).isInstanceOf(TimeoutException.class);
        }
    }
    
    @Nested
    @DisplayName("executeWithRetry")
    class ExecuteWithRetry {
        
        @Test
        @DisplayName("Повторяет при ошибке")
        @Timeout(10)
        void shouldRetryOnError() throws Exception {
            AtomicInteger attempts = new AtomicInteger(0);
            
            Integer result = TaskRunner.executeWithRetry(
                () -> {
                    if (attempts.incrementAndGet() < 3) {
                        throw new RuntimeException("Fail");
                    }
                    return 42;
                },
                5,
                Duration.ofMillis(10)
            );
            
            assertThat(result).isEqualTo(42);
            assertThat(attempts.get()).isEqualTo(3);
        }
        
        @Test
        @DisplayName("Выбрасывает исключение после всех попыток")
        void shouldThrowAfterMaxRetries() {
            assertThatThrownBy(() -> 
                TaskRunner.executeWithRetry(
                    () -> { throw new RuntimeException("Always fails"); },
                    3,
                    Duration.ofMillis(10)
                )
            ).isInstanceOf(RuntimeException.class);
        }
    }
    
    @Nested
    @DisplayName("shutdownGracefully")
    class ShutdownGracefully {
        
        @Test
        @DisplayName("Завершает executor корректно")
        @Timeout(5)
        void shouldShutdownGracefully() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> { Thread.sleep(100); return null; });
            
            boolean result = TaskRunner.shutdownGracefully(executor, Duration.ofSeconds(2));
            
            assertThat(result).isTrue();
            assertThat(executor.isShutdown()).isTrue();
        }
    }
}

