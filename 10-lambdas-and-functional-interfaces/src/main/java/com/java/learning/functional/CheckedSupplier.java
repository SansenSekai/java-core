package com.java.learning.functional;

import java.util.function.Supplier;

/**
 * Supplier, который может выбрасывать checked exception.
 * 
 * Стандартный Supplier не позволяет выбрасывать checked exceptions,
 * что затрудняет работу с IO, JDBC и т.д.
 * 
 * @param <T> тип результата
 */
@FunctionalInterface
public interface CheckedSupplier<T> {
    
    /**
     * Получает результат, возможно выбрасывая исключение.
     */
    T get() throws Exception;
    
    /**
     * Оборачивает checked exception в RuntimeException.
     * 
     * Пример использования:
     * <pre>
     * Supplier<Connection> supplier = CheckedSupplier.unchecked(
     *     () -> DriverManager.getConnection(url)
     * );
     * </pre>
     * 
     * @return обычный Supplier
     */
    static <T> Supplier<T> unchecked(CheckedSupplier<T> supplier) {
        // TODO: Реализуй метод
        // Подсказка: оберни вызов в try-catch и преобразуй исключение
        throw new UnsupportedOperationException("TODO: implement unchecked");
    }
    
    /**
     * Оборачивает с кастомным преобразованием исключения.
     * 
     * @param exceptionMapper функция для преобразования Exception в RuntimeException
     */
    static <T> Supplier<T> unchecked(
            CheckedSupplier<T> supplier,
            java.util.function.Function<Exception, RuntimeException> exceptionMapper) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement unchecked with mapper");
    }
}

