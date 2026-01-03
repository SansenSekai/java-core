package com.java.learning.handles;

import java.lang.invoke.*;

/**
 * Примеры использования VarHandle (Java 9+).
 * 
 * VarHandle — типобезопасный доступ к полям с поддержкой атомарных операций.
 * 
 * Режимы доступа:
 * - Plain: обычный доступ (как поле)
 * - Opaque: гарантия порядка для одной переменной
 * - Acquire/Release: acquire-release семантика
 * - Volatile: полная volatile семантика
 * 
 * Атомарные операции:
 * - compareAndSet: CAS
 * - getAndAdd: атомарный инкремент
 * - getAndSet: атомарный обмен
 * 
 * TODO: Изучи примеры и реализуй lock-free структуры
 */
public class VarHandleExamples {
    
    /**
     * Lock-free счётчик на VarHandle.
     * 
     * Эквивалент AtomicInteger, но более гибкий.
     */
    public static class LockFreeCounter {
        
        private volatile int value;
        
        private static final VarHandle VALUE;
        
        static {
            try {
                VALUE = MethodHandles.lookup()
                    .findVarHandle(LockFreeCounter.class, "value", int.class);
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        
        /**
         * Атомарный инкремент.
         * 
         * @return значение до инкремента
         */
        public int getAndIncrement() {
            // TODO: Реализуй через VarHandle
            // Подсказка: VALUE.getAndAdd(this, 1)
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Compare-and-set.
         */
        public boolean compareAndSet(int expected, int newValue) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Volatile чтение.
         */
        public int get() {
            return (int) VALUE.getVolatile(this);
        }
        
        /**
         * Volatile запись.
         */
        public void set(int value) {
            VALUE.setVolatile(this, value);
        }
    }
    
    /**
     * Lock-free стек на VarHandle.
     * 
     * Использует CAS для потокобезопасных операций push/pop.
     */
    public static class LockFreeStack<E> {
        
        private static class Node<E> {
            final E item;
            Node<E> next;
            
            Node(E item) {
                this.item = item;
            }
        }
        
        private volatile Node<E> top;
        
        private static final VarHandle TOP;
        
        static {
            try {
                TOP = MethodHandles.lookup()
                    .findVarHandle(LockFreeStack.class, "top", Node.class);
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        
        /**
         * Добавляет элемент на вершину стека.
         */
        public void push(E item) {
            // TODO: Реализуй lock-free push
            // Алгоритм:
            // 1. Создай новый узел
            // 2. CAS loop: node.next = top; CAS(top, node.next, node)
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Извлекает элемент с вершины стека.
         */
        public E pop() {
            // TODO: Реализуй lock-free pop
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

