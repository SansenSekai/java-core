package com.java.learning.reflection;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * Утилиты для работы с Reflection API.
 * 
 * Reflection позволяет:
 * - Инспектировать классы, методы, поля во время выполнения
 * - Создавать экземпляры классов динамически
 * - Вызывать методы и изменять поля
 * - Обходить модификаторы доступа (с setAccessible)
 * 
 * Предупреждение: Reflection медленнее обычного кода и нарушает инкапсуляцию.
 * Используй только когда действительно необходимо.
 * 
 * TODO: Реализуй все методы
 */
public class ReflectionUtils {
    
    /**
     * Создаёт экземпляр класса по его имени.
     * 
     * @param className полное имя класса (например, "java.util.ArrayList")
     * @param constructorArgs аргументы конструктора
     * @return новый экземпляр
     */
    public static Object createInstance(String className, Object... constructorArgs) 
            throws ClassNotFoundException, NoSuchMethodException, 
                   InvocationTargetException, InstantiationException, IllegalAccessException {
        // TODO: Реализуй
        // 1. Загрузи класс через Class.forName
        // 2. Найди подходящий конструктор
        // 3. Создай экземпляр
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Копирует значения полей из одного объекта в другой.
     * 
     * Копирует только поля с одинаковыми именами и совместимыми типами.
     */
    public static void copyFields(Object source, Object target) throws IllegalAccessException {
        // TODO: Реализуй
        // Подсказка: getDeclaredFields, setAccessible
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает все методы класса (включая приватные и унаследованные).
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        // TODO: Реализуй
        // Подсказка: обойди всю иерархию классов через getSuperclass
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Вызывает приватный метод.
     */
    public static Object invokePrivateMethod(
            Object target, 
            String methodName, 
            Object... args) throws Exception {
        // TODO: Реализуй
        // Подсказка: getDeclaredMethod, setAccessible, invoke
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Получает значение приватного поля.
     */
    public static Object getPrivateField(Object target, String fieldName) throws Exception {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Устанавливает значение приватного поля.
     */
    public static void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, является ли класс immutable.
     * 
     * Класс считается immutable, если:
     * - Все поля final
     * - Нет сеттеров
     * - Все поля имеют immutable типы (примитивы, String, другие immutable)
     */
    public static boolean isImmutable(Class<?> clazz) {
        // TODO: Реализуй (упрощённую версию)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт "глубокую" копию объекта через reflection.
     * 
     * Предупреждение: упрощённая реализация, не обрабатывает все случаи.
     */
    public static <T> T deepCopy(T original) throws Exception {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

