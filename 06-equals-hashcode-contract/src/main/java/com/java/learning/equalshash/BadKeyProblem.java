package com.java.learning.equalshash;

import java.util.HashMap;
import java.util.Map;

/**
 * ПРОБЛЕМА: Этот класс используется как ключ HashMap,
 * но элементы "теряются" после изменения объекта.
 * 
 * TODO: Найди и исправь ошибку в классе BadKey
 */
public class BadKeyProblem {
    
    /**
     * Демонстрация проблемы.
     */
    public static void main(String[] args) {
        Map<BadKey, String> map = new HashMap<>();
        
        BadKey key = new BadKey("initial");
        map.put(key, "value");
        
        System.out.println("Before mutation: " + map.get(key)); // "value"
        
        key.setName("mutated"); // ПРОБЛЕМА: изменяем ключ!
        
        System.out.println("After mutation: " + map.get(key));  // null! Потерялось!
        System.out.println("Map size: " + map.size());          // 1 - элемент есть, но не найти
        
        // Почему это происходит?
        // 1. hashCode вычисляется при put()
        // 2. После изменения поля hashCode становится другим
        // 3. get() ищет по новому hashCode в другом bucket'е
        // 4. Элемент "потерян"
    }
    
    /**
     * ПЛОХОЙ класс — мутабельные поля участвуют в equals/hashCode.
     */
    public static class BadKey {
        private String name; // мутабельное поле!
        
        public BadKey(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name; // Изменение ломает hashCode!
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BadKey badKey = (BadKey) o;
            return name.equals(badKey.name);
        }
        
        @Override
        public int hashCode() {
            return name.hashCode(); // Зависит от мутабельного поля!
        }
    }
    
    /**
     * ХОРОШИЙ класс — все поля final.
     * 
     * TODO: Реализуй этот класс как immutable
     */
    public static final class GoodKey {
        // TODO: Сделай поле final
        // TODO: Убери setter
        // TODO: Реализуй equals/hashCode
        
        public GoodKey(String name) {
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

