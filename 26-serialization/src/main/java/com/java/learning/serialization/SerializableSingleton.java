package com.java.learning.serialization;

import java.io.*;

/**
 * Singleton, защищённый от "клонирования" через сериализацию.
 * 
 * Проблема: при десериализации создаётся новый объект,
 * нарушая контракт Singleton.
 * 
 * Решение: метод readResolve() возвращает существующий экземпляр.
 * 
 * TODO: Реализуй защищённый Singleton
 */
public class SerializableSingleton implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private static final SerializableSingleton INSTANCE = new SerializableSingleton();
    
    private final String state;
    
    private SerializableSingleton() {
        this.state = "initialized";
    }
    
    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }
    
    public String getState() {
        return state;
    }
    
    /**
     * Защита от создания дубликата при десериализации.
     * 
     * Вызывается после readObject, позволяет заменить десериализованный
     * объект на другой.
     */
    @Serial
    private Object readResolve() throws ObjectStreamException {
        // TODO: Верни существующий экземпляр вместо десериализованного
        throw new UnsupportedOperationException("TODO: implement");
    }
}

