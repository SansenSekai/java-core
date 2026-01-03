package com.java.learning.classloaders;

import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hot Reload — перезагрузка классов без перезапуска приложения.
 * 
 * Важно: в Java нельзя "перезагрузить" класс в том же ClassLoader.
 * Нужно создать новый ClassLoader и загрузить класс заново.
 * 
 * Ограничения:
 * - Существующие экземпляры старого класса останутся старыми
 * - Нужно создавать новые экземпляры через новый ClassLoader
 * 
 * TODO: Реализуй простой hot reload
 */
public class HotReloader {
    
    private final Path classesDirectory;
    private final Map<String, ClassLoader> loaders = new ConcurrentHashMap<>();
    private final Map<String, Long> lastModified = new ConcurrentHashMap<>();
    
    public HotReloader(Path classesDirectory) {
        this.classesDirectory = classesDirectory;
    }
    
    /**
     * Загружает класс, перезагружая если файл изменился.
     */
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        // TODO: Реализуй
        // 1. Проверь, изменился ли файл
        // 2. Если да — создай новый ClassLoader и загрузи класс
        // 3. Если нет — используй существующий
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новый экземпляр класса (с hot reload если нужно).
     */
    @SuppressWarnings("unchecked")
    public <T> T createInstance(String className) throws Exception {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, изменился ли файл класса.
     */
    private boolean hasChanged(String className) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

