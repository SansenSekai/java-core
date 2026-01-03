package com.java.learning.jmm;

/**
 * Примеры проблем видимости в Java Memory Model.
 * 
 * JMM определяет, как потоки взаимодействуют через память:
 * - Каждый поток может иметь локальный кэш значений
 * - Без синхронизации изменения могут быть невидимы другим потокам
 * 
 * Happens-before — ключевое понятие JMM:
 * - Если A happens-before B, то все изменения A видны в B
 * 
 * TODO: Изучи примеры и пойми, почему они работают/не работают
 */
public class VisibilityExamples {
    
    /**
     * Пример 1: Проблема видимости без volatile.
     * 
     * Этот код может зависнуть навсегда!
     * Поток reader может никогда не увидеть flag = true.
     */
    public static class NoVisibility {
        private boolean flag = false;  // Должен быть volatile!
        private int value = 0;
        
        public void writer() {
            value = 42;
            flag = true;
        }
        
        public void reader() {
            // Может зависнуть навсегда!
            while (!flag) {
                // spin
            }
            // Даже если увидим flag=true, value может быть 0 (reordering!)
            System.out.println(value);
        }
    }
    
    /**
     * Пример 2: Исправление с volatile.
     * 
     * volatile гарантирует:
     * 1. Видимость: запись видна всем потокам
     * 2. Happens-before: запись volatile happens-before чтение
     */
    public static class WithVolatile {
        public volatile boolean flag = false;
        public int value = 0;  // Теперь тоже видим благодаря happens-before
        
        public void writer() {
            value = 42;      // (1) Запись в value
            flag = true;     // (2) Запись в volatile
            // (1) happens-before (2) из-за program order
        }
        
        public void reader() {
            while (!flag) {  // (3) Чтение volatile
                // spin
            }
            // (2) happens-before (3) из-за volatile
            // Следовательно, (1) happens-before (3)
            System.out.println(value);  // Гарантированно 42
        }
    }
    
    /**
     * Пример 3: Double-checked locking (неправильно!).
     */
    public static class BrokenDCL {
        private static Object instance;  // Должен быть volatile!
        
        public static Object getInstance() {
            if (instance == null) {
                synchronized (BrokenDCL.class) {
                    if (instance == null) {
                        // Опасно! Другой поток может увидеть
                        // частично сконструированный объект
                        instance = new Object();
                    }
                }
            }
            return instance;
        }
    }
    
    /**
     * Пример 4: Правильный Double-checked locking.
     */
    public static class CorrectDCL {
        private static volatile Object instance;  // volatile!
        
        public static Object getInstance() {
            Object local = instance;  // Локальная переменная для производительности
            if (local == null) {
                synchronized (CorrectDCL.class) {
                    local = instance;
                    if (local == null) {
                        instance = local = new Object();
                    }
                }
            }
            return local;
        }
    }
}

