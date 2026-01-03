package com.java.learning.inheritance.notification;

import java.util.List;

/**
 * Составной уведомитель, который отправляет через несколько каналов.
 * 
 * Паттерн Composite: позволяет работать с группой объектов так же,
 * как с одним объектом.
 * 
 * TODO: Реализуй этот класс
 */
public class CompositeNotifier implements Notifier {
    
    // TODO: Добавь поле для списка notifiers
    
    /**
     * Создаёт составной уведомитель с указанными каналами.
     * 
     * @param notifiers список уведомителей
     * @throws IllegalArgumentException если список пуст или null
     */
    public CompositeNotifier(List<Notifier> notifiers) {
        // TODO: Реализуй конструктор
        // ВАЖНО: сделай defensive copy списка!
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт составной уведомитель с указанными каналами.
     * 
     * @param notifiers уведомители (varargs)
     */
    public CompositeNotifier(Notifier... notifiers) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean send(String message, String recipient) {
        // TODO: Реализуй отправку через все каналы
        // Возвращай true, если хотя бы один канал успешен
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getType() {
        return "COMPOSITE";
    }
    
    /**
     * Отправляет через все каналы и возвращает количество успешных отправок.
     */
    public int sendToAll(String message, String recipient) {
        // TODO: Реализуй отправку с подсчётом успехов
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает список всех уведомителей.
     */
    public List<Notifier> getNotifiers() {
        // TODO: Реализуй геттер
        // ВАЖНО: верни неизменяемый список!
        throw new UnsupportedOperationException("TODO: implement");
    }
}

