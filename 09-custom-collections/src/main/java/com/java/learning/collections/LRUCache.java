package com.java.learning.collections;

import java.util.Map;

/**
 * LRU (Least Recently Used) Cache - кэш с вытеснением редко используемых элементов.
 * 
 * При переполнении удаляется элемент, к которому дольше всего не обращались.
 * 
 * Требования к реализации:
 * - get(key) должен работать за O(1)
 * - put(key, value) должен работать за O(1)
 * - При get() элемент должен становиться "самым свежим"
 * 
 * TODO: Реализуй LRU Cache
 * 
 * Подсказка: используй LinkedHashMap с accessOrder=true
 * или реализуй свой doubly-linked list + HashMap
 * 
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class LRUCache<K, V> {
    
    private final int capacity;
    
    // TODO: Добавь необходимые структуры данных
    // Подсказка: Map для быстрого доступа + связный список для порядка
    
    /**
     * Создаёт кэш с заданной ёмкостью.
     * 
     * @param capacity максимальное количество элементов
     * @throws IllegalArgumentException если capacity <= 0
     */
    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        // TODO: Инициализируй структуры данных
    }
    
    /**
     * Получает значение по ключу.
     * Элемент становится "самым свежим".
     * 
     * @param key ключ
     * @return значение или null, если ключ отсутствует
     */
    public V get(K key) {
        // TODO: Реализуй метод
        // 1. Найти элемент
        // 2. Переместить его в конец (сделать самым свежим)
        // 3. Вернуть значение
        throw new UnsupportedOperationException("TODO: implement get");
    }
    
    /**
     * Добавляет или обновляет элемент.
     * При переполнении удаляется самый старый элемент.
     * 
     * @param key ключ
     * @param value значение
     */
    public void put(K key, V value) {
        // TODO: Реализуй метод
        // 1. Если ключ есть - обновить значение и переместить в конец
        // 2. Если нет места - удалить самый старый
        // 3. Добавить новый элемент в конец
        throw new UnsupportedOperationException("TODO: implement put");
    }
    
    /**
     * Возвращает текущий размер кэша.
     */
    public int size() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement size");
    }
    
    /**
     * Проверяет наличие ключа.
     * НЕ влияет на порядок (не делает элемент "свежим").
     */
    public boolean containsKey(K key) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement containsKey");
    }
    
    /**
     * Удаляет элемент по ключу.
     * 
     * @return удалённое значение или null
     */
    public V remove(K key) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement remove");
    }
    
    /**
     * Очищает кэш.
     */
    public void clear() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement clear");
    }
}

