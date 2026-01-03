package com.java.learning.serialization;

import java.io.*;

/**
 * Утилиты для сериализации.
 * 
 * TODO: Реализуй все методы
 */
public class SerializationUtils {
    
    /**
     * Сериализует объект в массив байтов.
     */
    public static byte[] serialize(Serializable obj) throws IOException {
        // TODO: Реализуй
        // Подсказка: ByteArrayOutputStream + ObjectOutputStream
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Десериализует объект из массива байтов.
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт глубокую копию объекта через сериализацию.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T obj) throws IOException, ClassNotFoundException {
        // TODO: Реализуй
        // Подсказка: serialize + deserialize
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Сохраняет объект в файл.
     */
    public static void saveToFile(Serializable obj, String filename) throws IOException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Загружает объект из файла.
     */
    @SuppressWarnings("unchecked")
    public static <T> T loadFromFile(String filename) throws IOException, ClassNotFoundException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

