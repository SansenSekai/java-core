package com.java.learning.proxy;

import java.lang.reflect.*;

/**
 * Динамический прокси для автоматических повторных попыток.
 * 
 * При исключении повторяет вызов метода заданное количество раз.
 * 
 * TODO: Реализуй
 */
public class RetryProxy {
    
    /**
     * Создаёт прокси с повторными попытками.
     * 
     * @param target целевой объект
     * @param interfaceType интерфейс
     * @param maxRetries максимальное количество повторов
     * @param retryableExceptions исключения, при которых нужно повторять
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> T create(
            T target, 
            Class<T> interfaceType,
            int maxRetries,
            Class<? extends Exception>... retryableExceptions) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

