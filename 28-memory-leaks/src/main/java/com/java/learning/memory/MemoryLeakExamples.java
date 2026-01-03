package com.java.learning.memory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Примеры типичных утечек памяти в Java.
 * 
 * Утечка памяти — ситуация, когда объекты больше не нужны,
 * но GC не может их удалить из-за оставшихся ссылок.
 * 
 * TODO: Найди и исправь утечки памяти в каждом примере
 */
public class MemoryLeakExamples {
    
    /**
     * Утечка 1: Забытые слушатели.
     * 
     * Проблема: регистрируем слушатель, но не удаляем его.
     */
    public static class EventSystem {
        private final List<Runnable> listeners = new ArrayList<>();
        
        public void addListener(Runnable listener) {
            listeners.add(listener);
        }
        
        // TODO: Добавь метод removeListener
        // TODO: Или используй WeakReference для слушателей
        
        public void fireEvent() {
            listeners.forEach(Runnable::run);
        }
    }
    
    /**
     * Утечка 2: Статическая коллекция.
     * 
     * Проблема: объекты никогда не удаляются из статической коллекции.
     */
    public static class Cache {
        // ПЛОХО: статический Map, который только растёт
        private static final Map<String, Object> cache = new HashMap<>();
        
        public static void put(String key, Object value) {
            cache.put(key, value);
        }
        
        public static Object get(String key) {
            return cache.get(key);
        }
        
        // TODO: Добавь метод remove или используй WeakHashMap
        // TODO: Или добавь политику вытеснения (LRU)
    }
    
    /**
     * Утечка 3: Незакрытые ресурсы.
     * 
     * Проблема: ресурсы не закрываются, держат память.
     */
    public static class ResourceHolder {
        
        public String readFile(String path) throws Exception {
            // ПЛОХО: ресурс не закрывается при исключении
            var reader = new java.io.FileReader(path);
            var buffer = new char[1024];
            reader.read(buffer);
            return new String(buffer);
            
            // TODO: Используй try-with-resources
        }
    }
    
    /**
     * Утечка 4: Внутренний класс держит ссылку на внешний.
     */
    public static class OuterClass {
        private final byte[] largeData = new byte[10_000_000]; // 10MB
        
        // ПЛОХО: нестатический внутренний класс держит ссылку на OuterClass
        public class InnerTask implements Runnable {
            @Override
            public void run() {
                // Делает что-то, не использует largeData
                System.out.println("Running task");
            }
        }
        
        // TODO: Сделай InnerTask статическим классом
    }
    
    /**
     * Утечка 5: ThreadLocal без очистки.
     */
    public static class ThreadLocalLeak {
        // ПЛОХО: ThreadLocal не очищается
        private static final ThreadLocal<byte[]> threadLocalData = new ThreadLocal<>();
        
        public static void setData(byte[] data) {
            threadLocalData.set(data);
        }
        
        public static byte[] getData() {
            return threadLocalData.get();
        }
        
        // TODO: Добавь метод clear() и вызывай его в finally блоке
        // TODO: Или используй try-finally для очистки
    }
}

