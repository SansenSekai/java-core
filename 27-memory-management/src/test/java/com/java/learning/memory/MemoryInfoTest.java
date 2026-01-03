package com.java.learning.memory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Memory Info")
class MemoryInfoTest {

    @Test
    @DisplayName("getHeapInfo возвращает информацию о heap")
    void shouldGetHeapInfo() {
        var info = MemoryInfo.getHeapInfo();
        
        assertThat(info.used()).isPositive();
        assertThat(info.max()).isPositive();
        assertThat(info.usedPercent()).isBetween(0.0, 100.0);
    }
    
    @Test
    @DisplayName("getNonHeapInfo возвращает информацию о metaspace")
    void shouldGetNonHeapInfo() {
        var info = MemoryInfo.getNonHeapInfo();
        
        assertThat(info.used()).isPositive();
    }
    
    @Test
    @DisplayName("getGCCount возвращает количество GC")
    void shouldGetGCCount() {
        long count = MemoryInfo.getGCCount();
        
        // Счётчик может быть 0 или положительным
        assertThat(count).isGreaterThanOrEqualTo(0);
    }
    
    @Test
    @DisplayName("HeapInfo.toString форматирует корректно")
    void heapInfoToStringShouldFormat() {
        var info = MemoryInfo.getHeapInfo();
        String str = info.toString();
        
        assertThat(str).contains("MB");
        assertThat(str).contains("%");
    }
}

