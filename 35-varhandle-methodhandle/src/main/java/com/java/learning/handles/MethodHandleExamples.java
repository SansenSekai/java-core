package com.java.learning.handles;

import java.lang.invoke.*;

/**
 * Примеры использования MethodHandle.
 * 
 * MethodHandle — типобезопасная альтернатива Reflection:
 * - Быстрее (JIT может инлайнить)
 * - Типобезопасность через MethodType
 * - Можно комбинировать (adapter patterns)
 * 
 * TODO: Изучи примеры и реализуй задания
 */
public class MethodHandleExamples {
    
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    
    /**
     * Создаёт MethodHandle для вызова метода по имени.
     * 
     * @param clazz класс
     * @param methodName имя метода
     * @param returnType тип возвращаемого значения
     * @param paramTypes типы параметров
     */
    public static MethodHandle findMethod(
            Class<?> clazz, 
            String methodName,
            Class<?> returnType,
            Class<?>... paramTypes) throws NoSuchMethodException, IllegalAccessException {
        // TODO: Реализуй
        // Подсказка: LOOKUP.findVirtual или findStatic
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт MethodHandle для конструктора.
     */
    public static MethodHandle findConstructor(
            Class<?> clazz,
            Class<?>... paramTypes) throws NoSuchMethodException, IllegalAccessException {
        // TODO: Реализуй
        // Подсказка: LOOKUP.findConstructor
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт MethodHandle для геттера поля.
     */
    public static MethodHandle findGetter(
            Class<?> clazz,
            String fieldName,
            Class<?> fieldType) throws NoSuchFieldException, IllegalAccessException {
        // TODO: Реализуй
        // Подсказка: LOOKUP.findGetter
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт MethodHandle для сеттера поля.
     */
    public static MethodHandle findSetter(
            Class<?> clazz,
            String fieldName,
            Class<?> fieldType) throws NoSuchFieldException, IllegalAccessException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Демонстрация комбинаторов.
     * 
     * MethodHandles позволяют трансформировать handles:
     * - insertArguments: фиксирует аргументы (partial application)
     * - filterReturnValue: трансформирует результат
     * - catchException: обрабатывает исключения
     */
    public static void combinatorExamples() throws Throwable {
        // TODO: Изучи примеры комбинаторов
        
        // Пример: String.length()
        MethodHandle length = LOOKUP.findVirtual(
            String.class, "length", MethodType.methodType(int.class)
        );
        
        // Фиксируем "hello" как аргумент
        MethodHandle helloLength = length.bindTo("hello");
        int len = (int) helloLength.invoke(); // 5
        
        // Конвертируем результат в String
        MethodHandle intToString = LOOKUP.findStatic(
            String.class, "valueOf", MethodType.methodType(String.class, int.class)
        );
        MethodHandle lengthAsString = MethodHandles.filterReturnValue(length, intToString);
        String result = (String) lengthAsString.invoke("hello"); // "5"
    }
}

