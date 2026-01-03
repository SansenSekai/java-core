package com.java.learning.serialization;

import java.io.*;
import java.util.List;

/**
 * Пример класса с кастомной сериализацией.
 * 
 * Serializable — маркерный интерфейс для стандартной сериализации.
 * 
 * Важные методы:
 * - writeObject/readObject — кастомизация сериализации
 * - writeReplace/readResolve — замена объектов
 * 
 * TODO: Реализуй правильную сериализацию
 */
public class SerializableEntity implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String name;
    private transient String password; // Не сериализуется!
    private List<String> tags;
    
    // TODO: Добавь конструкторы и геттеры
    
    public SerializableEntity(String name, String password, List<String> tags) {
        this.name = name;
        this.password = password;
        this.tags = tags;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    /**
     * Кастомная сериализация.
     * 
     * Позволяет контролировать, что и как записывается.
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        // TODO: Реализуй
        // 1. Вызови defaultWriteObject() для стандартных полей
        // 2. Запиши дополнительные данные если нужно
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Кастомная десериализация.
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // TODO: Реализуй
        // 1. Вызови defaultReadObject()
        // 2. Восстанови transient поля если нужно
        throw new UnsupportedOperationException("TODO: implement");
    }
}

