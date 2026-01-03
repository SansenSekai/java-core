package com.java.learning.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Упрощённая реализация двусвязного списка.
 * 
 * TODO: Реализуй этот класс
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    
    // TODO: Добавь поля:
    // - Node<E> first (первый узел)
    // - Node<E> last (последний узел)  
    // - int size
    
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    
    public void addFirst(E element) {
        // TODO: Добавь элемент в начало
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void addLast(E element) {
        // TODO: Добавь элемент в конец
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E removeFirst() {
        // TODO: Удали и верни первый элемент
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E removeLast() {
        // TODO: Удали и верни последний элемент
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E getFirst() {
        // TODO: Верни первый элемент
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E getLast() {
        // TODO: Верни последний элемент
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public E get(int index) {
        // TODO: Верни элемент по индексу
        // Оптимизация: если index < size/2, иди от начала, иначе от конца
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void add(E element) {
        addLast(element);
    }
    
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public void clear() {
        // TODO: Очисти список
        // Обнули все ссылки для помощи GC
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public Iterator<E> iterator() {
        // TODO: Верни итератор
        throw new UnsupportedOperationException("TODO: implement");
    }
}

