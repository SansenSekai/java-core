package com.java.learning.async;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Async Operations")
class AsyncOperationsTest {

    private final Executor executor = Executors.newFixedThreadPool(4);
    
    @Test
    @DisplayName("withTimeout завершается успешно до таймаута")
    @Timeout(5)
    void shouldCompleteBeforeTimeout() {
        CompletableFuture<String> future = AsyncOperations.withTimeout(
            () -> {
                sleep(100);
                return "result";
            },
            Duration.ofSeconds(1),
            executor
        );
        
        assertThat(future.join()).isEqualTo("result");
    }
    
    @Test
    @DisplayName("withTimeout выбрасывает TimeoutException при превышении таймаута")
    @Timeout(5)
    void shouldTimeoutOnSlowOperation() {
        CompletableFuture<String> future = AsyncOperations.withTimeout(
            () -> {
                sleep(2000);
                return "result";
            },
            Duration.ofMillis(100),
            executor
        );
        
        assertThatThrownBy(future::join)
            .hasCauseInstanceOf(TimeoutException.class);
    }
    
    @Test
    @DisplayName("allOf собирает все результаты")
    @Timeout(5)
    void shouldCollectAllResults() {
        List<CompletableFuture<Integer>> futures = List.of(
            CompletableFuture.supplyAsync(() -> 1),
            CompletableFuture.supplyAsync(() -> 2),
            CompletableFuture.supplyAsync(() -> 3)
        );
        
        List<Integer> result = AsyncOperations.allOf(futures).join();
        
        assertThat(result).containsExactly(1, 2, 3);
    }
    
    @Test
    @DisplayName("retry повторяет операцию при неудаче")
    @Timeout(10)
    void shouldRetryOnFailure() {
        AtomicInteger attempts = new AtomicInteger(0);
        
        CompletableFuture<String> future = AsyncOperations.retry(
            () -> {
                int attempt = attempts.incrementAndGet();
                if (attempt < 3) {
                    throw new RuntimeException("Attempt " + attempt + " failed");
                }
                return "success on attempt " + attempt;
            },
            5,
            Duration.ofMillis(100),
            executor
        );
        
        assertThat(future.join()).isEqualTo("success on attempt 3");
        assertThat(attempts.get()).isEqualTo(3);
    }
    
    @Test
    @DisplayName("withFallback использует fallback при ошибке")
    @Timeout(5)
    void shouldUseFallbackOnError() {
        CompletableFuture<String> primary = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("primary failed");
        });
        
        CompletableFuture<String> result = AsyncOperations.withFallback(
            primary,
            () -> CompletableFuture.completedFuture("fallback")
        );
        
        assertThat(result.join()).isEqualTo("fallback");
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

