package com.java.learning.collections;

import java.util.Iterator;

/**
 * Ring Buffer (Circular Buffer) - кольцевой буфер фиксированного размера.
 * 
 * При переполнении новые элементы перезаписывают старые.
 * 
 * Применение:
 * - Логирование последних N событий
 * - Потоковая обработка данных
 * - Producer-Consumer с ограниченным буфером
 * 
 * TODO: Реализуй Ring Buffer
 * 
 * @param <E> тип элементов
 */
public class RingBuffer<E> implements Iterable<E> {
    
    private final Object[] buffer;
    private int head;  // индекс для чтения
    private int tail;  // индекс для записи
    private int size;
    
    /**
     * Создаёт буфер заданной ёмкости.
     */
    public RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.buffer = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    /**
     * Добавляет элемент, если есть место.
     * 
     * @param element элемент
     * @return true если добавлен, false если буфер полон
     */
    public boolean offer(E element) {
        // TODO: Реализуй метод
        // Не перезаписывает существующие элементы
        throw new UnsupportedOperationException("TODO: implement offer");
    }
    
    /**
     * Добавляет элемент, перезаписывая старый при переполнении.
     * 
     * @param element элемент
     * @return перезаписанный элемент или null
     */
    @SuppressWarnings("unchecked")
    public E forceOffer(E element) {
        // TODO: Реализуй метод
        // При переполнении удаляет самый старый элемент
        throw new UnsupportedOperationException("TODO: implement forceOffer");
    }
    
    /**
     * Извлекает и удаляет самый старый элемент.
     * 
     * @return элемент или null если буфер пуст
     */
    @SuppressWarnings("unchecked")
    public E poll() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement poll");
    }
    
    /**
     * Возвращает самый старый элемент без удаления.
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement peek");
    }
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return buffer.length;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == buffer.length;
    }
    
    /**
     * Очищает буфер.
     */
    public void clear() {
        // TODO: Реализуй метод
        // Подсказка: обнули ссылки для GC
        throw new UnsupportedOperationException("TODO: implement clear");
    }
    
    @Override
    public Iterator<E> iterator() {
        // TODO: Реализуй итератор
        // Должен итерировать от head к tail
        throw new UnsupportedOperationException("TODO: implement iterator");
    }
}

