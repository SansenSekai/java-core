package com.java.learning.generics;

import java.util.Objects;

/**
 * Immutable пара значений.
 * 
 * TODO: Реализуй этот класс
 * 
 * @param <K> тип первого элемента
 * @param <V> тип второго элемента
 */
public final class Pair<K, V> {
    
    // TODO: Добавь final поля для first и second
    
    private Pair(K first, V second) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт пару из двух значений.
     */
    public static <K, V> Pair<K, V> of(K first, V second) {
        // TODO: Реализуй фабричный метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public K getFirst() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public V getSecond() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новую пару с изменённым первым элементом.
     */
    public <K2> Pair<K2, V> withFirst(K2 newFirst) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новую пару с изменённым вторым элементом.
     */
    public <V2> Pair<K, V2> withSecond(V2 newSecond) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Меняет местами элементы.
     */
    public Pair<V, K> swap() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String toString() {
        // TODO: Реализуй метод
        // Формат: "(first, second)"
        throw new UnsupportedOperationException("TODO: implement");
    }
}

