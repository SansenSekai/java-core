package com.java.learning.patterns.behavioral;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Паттерн Observer — подписка на события.
 * 
 * Subject уведомляет всех Observer'ов об изменениях.
 * 
 * Примеры в JDK:
 * - java.util.Observer (deprecated)
 * - java.beans.PropertyChangeListener
 * - java.util.concurrent.Flow (reactive streams)
 * 
 * TODO: Реализуй систему событий
 */
public class Observer {
    
    /**
     * Событие.
     */
    public record Event<T>(String type, T data, long timestamp) {
        public Event(String type, T data) {
            this(type, data, System.currentTimeMillis());
        }
    }
    
    /**
     * Издатель событий.
     * 
     * @param <T> тип данных события
     */
    public static class EventPublisher<T> {
        
        private final Map<String, List<Consumer<Event<T>>>> subscribers = new HashMap<>();
        
        /**
         * Подписаться на события определённого типа.
         * 
         * @return функция для отписки
         */
        public Runnable subscribe(String eventType, Consumer<Event<T>> handler) {
            // TODO: Реализуй
            // 1. Добавь handler в список
            // 2. Верни функцию, которая удаляет handler
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Подписаться на все события.
         */
        public Runnable subscribeAll(Consumer<Event<T>> handler) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Опубликовать событие.
         */
        public void publish(String eventType, T data) {
            // TODO: Реализуй
            // Уведоми всех подписчиков на этот тип + подписчиков на "*"
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        /**
         * Количество подписчиков на тип события.
         */
        public int subscriberCount(String eventType) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Потокобезопасный издатель.
     */
    public static class ThreadSafeEventPublisher<T> {
        
        private final Map<String, CopyOnWriteArrayList<Consumer<Event<T>>>> subscribers 
            = new java.util.concurrent.ConcurrentHashMap<>();
        
        // TODO: Реализуй аналогично, но потокобезопасно
        
        public Runnable subscribe(String eventType, Consumer<Event<T>> handler) {
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public void publish(String eventType, T data) {
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

