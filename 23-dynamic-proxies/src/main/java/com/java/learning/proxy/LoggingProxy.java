package com.java.learning.proxy;

import java.lang.reflect.*;
import java.util.function.Consumer;

/**
 * Динамический прокси для логирования вызовов методов.
 * 
 * Паттерн Proxy через java.lang.reflect.Proxy:
 * - Работает только с интерфейсами
 * - Позволяет перехватывать все вызовы методов
 * - Основа для AOP (Aspect-Oriented Programming)
 * 
 * TODO: Реализуй все прокси
 */
public class LoggingProxy {
    
    /**
     * Создаёт прокси, который логирует все вызовы методов.
     * 
     * Пример вывода:
     * "Calling: someMethod(arg1, arg2)"
     * "Returned: result"
     * или
     * "Threw: SomeException: message"
     * 
     * @param target целевой объект
     * @param interfaceType интерфейс для прокси
     * @param logger функция для логирования
     */
    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<T> interfaceType, Consumer<String> logger) {
        // TODO: Реализуй
        // Подсказка: Proxy.newProxyInstance с InvocationHandler
        throw new UnsupportedOperationException("TODO: implement");
    }
}

