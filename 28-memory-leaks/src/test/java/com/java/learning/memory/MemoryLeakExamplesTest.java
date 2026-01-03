package com.java.learning.memory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Memory Leak Examples")
class MemoryLeakExamplesTest {

    /**
     * Тесты для этого модуля отличаются:
     * они не проверяют функциональность, а демонстрируют проблемы.
     * 
     * Задача студента: исправить код так, чтобы утечки не было.
     */
    
    @Test
    @DisplayName("EventSystem: слушатели регистрируются")
    void eventSystemShouldRegisterListeners() {
        var system = new MemoryLeakExamples.EventSystem();
        
        system.addListener(() -> System.out.println("Event!"));
        system.fireEvent();
        
        // TODO: После реализации removeListener, добавь:
        // system.removeListener(listener);
        // и проверь, что listener больше не вызывается
        
        assertThat(true).isTrue(); // Placeholder
    }
    
    @Test
    @DisplayName("Cache: статический кэш растёт бесконечно")
    void staticCacheShouldGrow() {
        // Демонстрация проблемы: добавляем много элементов
        for (int i = 0; i < 1000; i++) {
            MemoryLeakExamples.Cache.put("key" + i, new byte[1000]);
        }
        
        // Проблема: элементы никогда не удаляются!
        // TODO: После реализации политики вытеснения или WeakHashMap:
        // - добавить лимит на размер
        // - или использовать WeakHashMap для автоматической очистки
        
        assertThat(true).isTrue(); // Placeholder
    }
    
    @Test
    @DisplayName("ThreadLocal: очистка важна")
    void threadLocalShouldBeCleared() {
        // Устанавливаем данные
        MemoryLeakExamples.ThreadLocalLeak.setData(new byte[1_000_000]);
        
        // Проверяем, что данные есть
        assertThat(MemoryLeakExamples.ThreadLocalLeak.getData()).isNotNull();
        
        // TODO: После реализации clear():
        // ThreadLocalLeak.clear();
        // assertThat(ThreadLocalLeak.getData()).isNull();
        
        // Важно: в реальном коде всегда очищай ThreadLocal в finally!
    }
    
    @Test
    @DisplayName("Внутренний класс держит ссылку на внешний")
    void innerClassShouldNotHoldOuterReference() {
        // Создаём внешний класс с большими данными
        var outer = new MemoryLeakExamples.OuterClass();
        
        // Создаём задачу
        Runnable task = outer.new InnerTask();
        
        // Проблема: task держит ссылку на outer (и его 10MB данных)!
        // Даже если мы забудем про outer, GC не сможет его собрать,
        // пока существует task.
        
        // TODO: Сделать InnerTask статическим классом,
        // чтобы он не держал ссылку на OuterClass
        
        task.run();
        assertThat(true).isTrue(); // Placeholder
    }
}

