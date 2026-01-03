package com.java.learning.hashmap;

import java.util.Objects;

/**
 * Упрощённая реализация HashMap.
 * 
 * TODO: Реализуй этот класс
 */
public class SimpleHashMap<K, V> {
    
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    // TODO: Добавь поля:
    // - Node<K,V>[] table
    // - int size
    // - float loadFactor
    // - int threshold
    
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;
        
        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public SimpleHashMap() {
        // TODO: Инициализируй с дефолтными значениями
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public V put(K key, V value) {
        // TODO: Реализуй put
        // 1. Вычисли hash
        // 2. Найди bucket (hash & (table.length - 1))
        // 3. Если bucket пуст — создай Node
        // 4. Если занят — пройди по списку, ищи совпадение ключа
        // 5. Проверь threshold, сделай resize если нужно
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public V get(Object key) {
        // TODO: Реализуй get
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public V remove(Object key) {
        // TODO: Реализуй remove
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean containsKey(Object key) {
        return get(key) != null;
    }
    
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Вычисляет hash с распределением (spread).
     * Это важно для уменьшения коллизий.
     */
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    
    /**
     * Удваивает размер таблицы и перехэширует все элементы.
     */
    private void resize() {
        // TODO: Реализуй resize
        // 1. Создай новый массив двойного размера
        // 2. Пройди по всем элементам старого массива
        // 3. Перехэшируй каждый элемент в новый массив
        // 4. Обнови threshold
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает количество элементов в указанном bucket'е.
     * (для тестирования и анализа)
     */
    public int getBucketSize(int index) {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает длину самой длинной цепочки.
     */
    public int getLongestChain() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}

