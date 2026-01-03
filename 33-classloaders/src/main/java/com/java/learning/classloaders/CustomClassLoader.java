package com.java.learning.classloaders;

import java.io.*;
import java.nio.file.*;

/**
 * Кастомный ClassLoader.
 * 
 * Иерархия ClassLoader'ов в Java:
 * 1. Bootstrap ClassLoader (нативный) — загружает rt.jar
 * 2. Platform/Extension ClassLoader — загружает jre/lib/ext
 * 3. Application/System ClassLoader — загружает classpath
 * 4. Custom ClassLoaders — ваши загрузчики
 * 
 * Принцип делегирования: сначала спрашивает родителя.
 * 
 * TODO: Реализуй ClassLoader, загружающий классы из директории
 */
public class CustomClassLoader extends ClassLoader {
    
    private final Path classesDirectory;
    
    public CustomClassLoader(Path classesDirectory) {
        this(classesDirectory, ClassLoader.getSystemClassLoader());
    }
    
    public CustomClassLoader(Path classesDirectory, ClassLoader parent) {
        super(parent);
        this.classesDirectory = classesDirectory;
    }
    
    /**
     * Находит и загружает класс.
     * 
     * Вызывается, если родительский ClassLoader не нашёл класс.
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // TODO: Реализуй
        // 1. Преобразуй имя класса в путь к файлу
        //    com.example.MyClass -> com/example/MyClass.class
        // 2. Прочитай байткод из файла
        // 3. Вызови defineClass
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразует имя класса в путь к .class файлу.
     */
    private Path classNameToPath(String className) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Читает байткод класса из файла.
     */
    private byte[] loadClassBytes(Path path) throws IOException {
        // TODO: Реализуй
        // Подсказка: Files.readAllBytes
        throw new UnsupportedOperationException("TODO: implement");
    }
}

