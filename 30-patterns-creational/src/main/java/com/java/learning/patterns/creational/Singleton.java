package com.java.learning.patterns.creational;

/**
 * Паттерн Singleton — гарантирует единственный экземпляр класса.
 * 
 * Способы реализации:
 * 1. Eager initialization (простой, но создаёт при загрузке класса)
 * 2. Lazy initialization с synchronized (медленно из-за блокировки)
 * 3. Double-checked locking (требует volatile)
 * 4. Bill Pugh (через holder class) — рекомендуемый
 * 5. Enum (защищён от сериализации и reflection)
 * 
 * TODO: Реализуй все варианты и пойми их trade-offs
 */
public class Singleton {
    
    /**
     * Вариант 1: Eager initialization.
     * 
     * + Простой и потокобезопасный
     * - Создаётся даже если не используется
     */
    public static class EagerSingleton {
        // TODO: Реализуй
        // Подсказка: private static final INSTANCE = new EagerSingleton()
        
        public static EagerSingleton getInstance() {
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Вариант 2: Lazy с synchronized.
     * 
     * + Ленивая инициализация
     * - Блокировка при каждом вызове getInstance()
     */
    public static class SynchronizedSingleton {
        private static SynchronizedSingleton instance;
        
        private SynchronizedSingleton() {}
        
        public static synchronized SynchronizedSingleton getInstance() {
            // TODO: Реализуй lazy initialization
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Вариант 3: Double-Checked Locking.
     * 
     * + Блокировка только при первом создании
     * ! Требует volatile для корректности
     */
    public static class DCLSingleton {
        private static volatile DCLSingleton instance;
        
        private DCLSingleton() {}
        
        public static DCLSingleton getInstance() {
            // TODO: Реализуй DCL
            // Подсказка: 
            // if (instance == null) {
            //     synchronized (DCLSingleton.class) {
            //         if (instance == null) {
            //             instance = new DCLSingleton();
            //         }
            //     }
            // }
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Вариант 4: Bill Pugh (Holder).
     * 
     * + Ленивый, потокобезопасный, без synchronized
     * Рекомендуемый способ!
     */
    public static class HolderSingleton {
        private HolderSingleton() {}
        
        // TODO: Создай private static class Holder с final полем INSTANCE
        
        public static HolderSingleton getInstance() {
            // TODO: return Holder.INSTANCE
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Вариант 5: Enum Singleton.
     * 
     * + Защищён от сериализации и reflection
     * + Простой и надёжный
     * - Нельзя наследовать
     */
    public enum EnumSingleton {
        INSTANCE;
        
        // TODO: Добавь методы и состояние
        
        public void doSomething() {
            // ...
        }
    }
}

