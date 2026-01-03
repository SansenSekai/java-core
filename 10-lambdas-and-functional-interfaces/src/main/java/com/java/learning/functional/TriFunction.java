package com.java.learning.functional;

/**
 * Функциональный интерфейс для функции с тремя аргументами.
 * 
 * Аналог BiFunction, но с тремя параметрами.
 * 
 * TODO: Реализуй методы compose и andThen
 * 
 * @param <A> тип первого аргумента
 * @param <B> тип второго аргумента
 * @param <C> тип третьего аргумента
 * @param <R> тип результата
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    
    /**
     * Применяет функцию к аргументам.
     */
    R apply(A a, B b, C c);
    
    /**
     * Возвращает функцию, которая сначала применяет эту функцию,
     * затем применяет after к результату.
     * 
     * @param after функция для применения к результату
     * @return композиция функций
     */
    default <V> TriFunction<A, B, C, V> andThen(java.util.function.Function<? super R, ? extends V> after) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement andThen");
    }
}

