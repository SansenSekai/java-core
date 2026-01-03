package com.java.learning.hashmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("HashMap Internals")
class HashMapTest {
    
    @Test
    @DisplayName("put и get работают")
    void shouldPutAndGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
        assertThat(map.get("three")).isEqualTo(3);
    }
    
    @Test
    @DisplayName("put обновляет значение")
    void shouldUpdateValue() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        
        map.put("key", "old");
        String oldValue = map.put("key", "new");
        
        assertThat(oldValue).isEqualTo("old");
        assertThat(map.get("key")).isEqualTo("new");
    }
    
    @Test
    @DisplayName("remove удаляет элемент")
    void shouldRemove() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.put("key", 42);
        
        Integer removed = map.remove("key");
        
        assertThat(removed).isEqualTo(42);
        assertThat(map.containsKey("key")).isFalse();
    }
    
    @Test
    @DisplayName("Работает с большим количеством элементов")
    void shouldHandleManyElements() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        
        for (int i = 0; i < 10000; i++) {
            map.put(i, "value" + i);
        }
        
        assertThat(map.size()).isEqualTo(10000);
        
        for (int i = 0; i < 10000; i++) {
            assertThat(map.get(i)).isEqualTo("value" + i);
        }
    }
    
    @Test
    @DisplayName("null ключ работает")
    void shouldHandleNullKey() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        
        map.put(null, "null value");
        
        assertThat(map.get(null)).isEqualTo("null value");
    }
    
    @Test
    @DisplayName("Коллизии обрабатываются корректно")
    void shouldHandleCollisions() {
        // Создаём ключи с одинаковым hash (mod table size)
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        
        // При размере таблицы 16, ключи 1 и 17 попадут в один bucket
        map.put(1, "one");
        map.put(17, "seventeen");
        map.put(33, "thirty-three");
        
        assertThat(map.get(1)).isEqualTo("one");
        assertThat(map.get(17)).isEqualTo("seventeen");
        assertThat(map.get(33)).isEqualTo("thirty-three");
    }
}

