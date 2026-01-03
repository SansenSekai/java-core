package com.java.learning.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Упрощённая реализация ArrayList.
 * 
 * TODO: Реализуй этот класс
 */
public class SimpleArrayList<E> implements Iterable<E> {
    
    private static final int DEFAULT_CAPACITY = 10;
    
    // TODO: Добавь поля:
    // - Object[] elements (массив элементов)
    // - int size (текущий размер)
    
    public SimpleArrayList() {
        // TODO: Инициализируй массив начальной ёмкости
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public SimpleArrayList(int initialCapacity) {
        // TODO: Инициализируй массив указанной ёмкости
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void add(E element) {
        // TODO: Добавь элемент в конец
        // Если массив полон — расширь его
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void add(int index, E element) {
        // TODO: Вставь элемент по индексу
        // Сдвинь все последующие элементы вправо
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @SuppressWarnings("unchecked")
    public E get(int index) {
        // TODO: Верни элемент по индексу
        // Проверь границы!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E set(int index, E element) {
        // TODO: Замени элемент, верни старое значение
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E remove(int index) {
        // TODO: Удали элемент по индексу
        // Сдвинь все последующие элементы влево
        // Обнули последний элемент (для GC)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean remove(Object o) {
        // TODO: Удали первое вхождение элемента
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void clear() {
        // TODO: Очисти список
        // ВАЖНО: обнули все элементы для GC!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает внутреннюю ёмкость массива.
     * (для тестирования)
     */
    public int capacity() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Уменьшает ёмкость до текущего размера.
     */
    public void trimToSize() {
        // TODO: Создай новый массив точно под size
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    private void ensureCapacity(int minCapacity) {
        // TODO: Расширь массив, если нужно
        // Новая ёмкость = oldCapacity + oldCapacity / 2
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        // TODO: Верни итератор
        throw new UnsupportedOperationException("TODO: implement");
    }
}

