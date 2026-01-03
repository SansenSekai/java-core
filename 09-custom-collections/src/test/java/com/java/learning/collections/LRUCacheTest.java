package com.java.learning.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LRU Cache")
class LRUCacheTest {

    @Nested
    @DisplayName("Базовые операции")
    class BasicOperations {
        
        private LRUCache<String, Integer> cache;
        
        @BeforeEach
        void setUp() {
            cache = new LRUCache<>(3);
        }
        
        @Test
        @DisplayName("put и get работают корректно")
        void shouldPutAndGet() {
            cache.put("a", 1);
            cache.put("b", 2);
            
            assertThat(cache.get("a")).isEqualTo(1);
            assertThat(cache.get("b")).isEqualTo(2);
            assertThat(cache.get("c")).isNull();
        }
        
        @Test
        @DisplayName("size возвращает правильное значение")
        void shouldTrackSize() {
            assertThat(cache.size()).isZero();
            
            cache.put("a", 1);
            assertThat(cache.size()).isEqualTo(1);
            
            cache.put("b", 2);
            assertThat(cache.size()).isEqualTo(2);
        }
        
        @Test
        @DisplayName("put обновляет существующее значение")
        void shouldUpdateExistingValue() {
            cache.put("a", 1);
            cache.put("a", 100);
            
            assertThat(cache.get("a")).isEqualTo(100);
            assertThat(cache.size()).isEqualTo(1);
        }
    }
    
    @Nested
    @DisplayName("LRU политика вытеснения")
    class EvictionPolicy {
        
        @Test
        @DisplayName("При переполнении удаляется самый старый элемент")
        void shouldEvictLeastRecentlyUsed() {
            LRUCache<String, Integer> cache = new LRUCache<>(3);
            
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3);
            // Кэш полон: [a, b, c]
            
            cache.put("d", 4);
            // "a" должен быть удалён: [b, c, d]
            
            assertThat(cache.get("a")).isNull();
            assertThat(cache.get("b")).isEqualTo(2);
            assertThat(cache.get("c")).isEqualTo(3);
            assertThat(cache.get("d")).isEqualTo(4);
        }
        
        @Test
        @DisplayName("get обновляет порядок (делает элемент свежим)")
        void getShouldUpdateOrder() {
            LRUCache<String, Integer> cache = new LRUCache<>(3);
            
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3);
            
            // Обращаемся к "a" - он становится самым свежим
            cache.get("a");
            
            // Добавляем "d" - должен удалиться "b" (не "a"!)
            cache.put("d", 4);
            
            assertThat(cache.get("a")).isEqualTo(1);  // "a" остался
            assertThat(cache.get("b")).isNull();       // "b" удалён
            assertThat(cache.get("c")).isEqualTo(3);
            assertThat(cache.get("d")).isEqualTo(4);
        }
        
        @Test
        @DisplayName("put существующего ключа обновляет порядок")
        void putExistingShouldUpdateOrder() {
            LRUCache<String, Integer> cache = new LRUCache<>(3);
            
            cache.put("a", 1);
            cache.put("b", 2);
            cache.put("c", 3);
            
            // Обновляем "a" - он становится самым свежим
            cache.put("a", 100);
            
            // Добавляем "d" - должен удалиться "b"
            cache.put("d", 4);
            
            assertThat(cache.get("a")).isEqualTo(100);
            assertThat(cache.get("b")).isNull();
        }
    }
    
    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {
        
        @Test
        @DisplayName("Кэш размером 1")
        void shouldWorkWithSizeOne() {
            LRUCache<String, Integer> cache = new LRUCache<>(1);
            
            cache.put("a", 1);
            assertThat(cache.get("a")).isEqualTo(1);
            
            cache.put("b", 2);
            assertThat(cache.get("a")).isNull();
            assertThat(cache.get("b")).isEqualTo(2);
        }
        
        @Test
        @DisplayName("Нельзя создать кэш с нулевой ёмкостью")
        void shouldRejectZeroCapacity() {
            assertThatThrownBy(() -> new LRUCache<>(0))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("containsKey не влияет на порядок")
        void containsKeyShouldNotAffectOrder() {
            LRUCache<String, Integer> cache = new LRUCache<>(2);
            
            cache.put("a", 1);
            cache.put("b", 2);
            
            // containsKey не должен делать "a" свежим
            cache.containsKey("a");
            
            cache.put("c", 3);
            
            // "a" должен быть удалён, т.к. containsKey не обновляет порядок
            assertThat(cache.get("a")).isNull();
            assertThat(cache.get("b")).isEqualTo(2);
        }
    }
}

