package com.java.learning.proxy;

import java.lang.reflect.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Динамический прокси для кэширования результатов методов.
 * 
 * Кэширует результаты методов по их аргументам.
 * Подходит для чистых функций (одинаковые аргументы — одинаковый результат).
 * 
 * TODO: Реализуй
 */
public class CachingProxy {
    
    /**
     * Создаёт прокси с кэшированием.
     * 
     * Кэширует результаты всех методов (кроме методов Object: equals, hashCode, toString).
     * Ключ кэша: имя метода + аргументы.
     */
    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceType) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

