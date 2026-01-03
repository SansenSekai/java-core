package com.java.learning.sync;

/**
 * Ограниченный буфер (Producer-Consumer) с использованием wait/notify.
 * 
 * Это классическая задача многопоточного программирования:
 * - Producer добавляет элементы
 * - Consumer забирает элементы
 * - Буфер имеет ограниченный размер
 * 
 * TODO: Реализуй потокобезопасный буфер
 * 
 * Подсказка:
 * - synchronized для взаимного исключения
 * - wait() когда буфер полон/пуст
 * - notifyAll() после добавления/извлечения
 * 
 * @param <E> тип элементов
 */
public class BoundedBuffer<E> {
    
    private final Object[] buffer;
    private int count;
    private int putIndex;
    private int takeIndex;
    
    public BoundedBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.buffer = new Object[capacity];
    }
    
    /**
     * Добавляет элемент. Блокируется, если буфер полон.
     * 
     * @throws InterruptedException если поток прерван
     */
    public synchronized void put(E element) throws InterruptedException {
        // TODO: Реализуй метод
        // 1. Пока буфер полон — wait()
        // 2. Добавь элемент
        // 3. notifyAll() — разбуди ожидающих потребителей
        throw new UnsupportedOperationException("TODO: implement put");
    }
    
    /**
     * Извлекает элемент. Блокируется, если буфер пуст.
     * 
     * @throws InterruptedException если поток прерван
     */
    @SuppressWarnings("unchecked")
    public synchronized E take() throws InterruptedException {
        // TODO: Реализуй метод
        // 1. Пока буфер пуст — wait()
        // 2. Извлеки элемент
        // 3. notifyAll() — разбуди ожидающих производителей
        throw new UnsupportedOperationException("TODO: implement take");
    }
    
    /**
     * Возвращает текущее количество элементов.
     */
    public synchronized int size() {
        return count;
    }
    
    /**
     * Проверяет, пуст ли буфер.
     */
    public synchronized boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Проверяет, полон ли буфер.
     */
    public synchronized boolean isFull() {
        return count == buffer.length;
    }
}

