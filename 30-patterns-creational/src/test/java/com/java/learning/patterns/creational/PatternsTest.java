package com.java.learning.patterns.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Creational Patterns")
class PatternsTest {

    @Nested
    @DisplayName("Singleton variants")
    class SingletonTests {
        
        @Test
        @DisplayName("EagerSingleton возвращает один экземпляр")
        void eagerSingletonShouldReturnSameInstance() {
            var instance1 = Singleton.EagerSingleton.getInstance();
            var instance2 = Singleton.EagerSingleton.getInstance();
            
            assertThat(instance1).isSameAs(instance2);
        }
        
        @Test
        @DisplayName("SynchronizedSingleton возвращает один экземпляр")
        void synchronizedSingletonShouldReturnSameInstance() {
            var instance1 = Singleton.SynchronizedSingleton.getInstance();
            var instance2 = Singleton.SynchronizedSingleton.getInstance();
            
            assertThat(instance1).isSameAs(instance2);
        }
        
        @Test
        @DisplayName("DCLSingleton потокобезопасен")
        void dclSingletonShouldBeThreadSafe() throws InterruptedException {
            int threadCount = 100;
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);
            AtomicReference<Object> firstInstance = new AtomicReference<>();
            
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    var instance = Singleton.DCLSingleton.getInstance();
                    firstInstance.compareAndSet(null, instance);
                    assertThat(instance).isSameAs(firstInstance.get());
                    latch.countDown();
                });
            }
            
            latch.await();
            executor.shutdown();
        }
        
        @Test
        @DisplayName("HolderSingleton возвращает один экземпляр")
        void holderSingletonShouldReturnSameInstance() {
            var instance1 = Singleton.HolderSingleton.getInstance();
            var instance2 = Singleton.HolderSingleton.getInstance();
            
            assertThat(instance1).isSameAs(instance2);
        }
        
        @Test
        @DisplayName("EnumSingleton гарантирует единственность")
        void enumSingletonShouldBeUnique() {
            var instance1 = Singleton.EnumSingleton.INSTANCE;
            var instance2 = Singleton.EnumSingleton.INSTANCE;
            
            assertThat(instance1).isSameAs(instance2);
        }
    }
    
    @Nested
    @DisplayName("Builder")
    class BuilderTests {
        
        @Test
        @DisplayName("Создаёт объект с обязательными полями")
        void shouldBuildWithRequiredFields() {
            var request = Builder.HttpRequest.builder("GET", "http://example.com")
                .build();
            
            assertThat(request.getMethod()).isEqualTo("GET");
            assertThat(request.getUrl()).isEqualTo("http://example.com");
        }
        
        @Test
        @DisplayName("Создаёт объект с опциональными полями")
        void shouldBuildWithOptionalFields() {
            var request = Builder.HttpRequest.builder("POST", "http://example.com")
                .header("Content-Type", "application/json")
                .body("{\"key\": \"value\"}")
                .timeout(5000)
                .followRedirects(false)
                .build();
            
            assertThat(request.getHeaders()).containsEntry("Content-Type", "application/json");
            assertThat(request.getBody()).isEqualTo("{\"key\": \"value\"}");
            assertThat(request.getTimeout()).isEqualTo(5000);
            assertThat(request.isFollowRedirects()).isFalse();
        }
        
        @Test
        @DisplayName("Использует дефолтные значения")
        void shouldUseDefaults() {
            var request = Builder.HttpRequest.builder("GET", "http://example.com")
                .build();
            
            assertThat(request.getTimeout()).isEqualTo(30_000);
            assertThat(request.isFollowRedirects()).isTrue();
        }
        
        @Test
        @DisplayName("Реджектит невалидный timeout")
        void shouldRejectInvalidTimeout() {
            assertThatThrownBy(() -> 
                Builder.HttpRequest.builder("GET", "http://example.com")
                    .timeout(-1)
                    .build()
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }
}

