package com.java.learning.functional;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Утилиты для работы с функциональными интерфейсами.
 * 
 * TODO: Реализуй все методы
 */
public final class FunctionUtils {
    
    private FunctionUtils() {
        // Utility class
    }
    
    /**
     * Каррирование BiFunction: преобразует (A, B) -> R в A -> (B -> R).
     * 
     * Пример:
     * <pre>
     * BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
     * Function<Integer, Function<Integer, Integer>> curriedAdd = curry(add);
     * Function<Integer, Integer> add5 = curriedAdd.apply(5);
     * int result = add5.apply(3); // 8
     * </pre>
     */
    public static <A, B, R> Function<A, Function<B, R>> curry(BiFunction<A, B, R> biFunction) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement curry");
    }
    
    /**
     * Обратное каррирование: A -> (B -> R) в (A, B) -> R.
     */
    public static <A, B, R> BiFunction<A, B, R> uncurry(Function<A, Function<B, R>> curriedFunction) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement uncurry");
    }
    
    /**
     * Частичное применение: фиксирует первый аргумент.
     * 
     * Пример:
     * <pre>
     * BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
     * Function<Integer, String> repeatHello = partial(repeat, "Hello");
     * String result = repeatHello.apply(3); // "HelloHelloHello"
     * </pre>
     */
    public static <A, B, R> Function<B, R> partial(BiFunction<A, B, R> biFunction, A fixedFirst) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement partial");
    }
    
    /**
     * Мемоизация: кэширует результаты вызовов.
     * 
     * Предупреждение: не потокобезопасно!
     */
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        // TODO: Реализуй метод
        // Подсказка: используй Map для кэширования
        throw new UnsupportedOperationException("TODO: implement memoize");
    }
    
    /**
     * Ленивая инициализация: вычисляет значение один раз при первом вызове.
     */
    public static <T> Supplier<T> lazy(Supplier<T> supplier) {
        // TODO: Реализуй метод
        // Подсказка: сохрани результат после первого вычисления
        throw new UnsupportedOperationException("TODO: implement lazy");
    }
    
    /**
     * Отрицание предиката.
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement not");
    }
    
    /**
     * Композиция: применяет функции последовательно.
     * compose(f, g, h).apply(x) == h(g(f(x)))
     */
    @SafeVarargs
    public static <T> Function<T, T> compose(Function<T, T>... functions) {
        // TODO: Реализуй метод
        // Подсказка: используй reduce с andThen
        throw new UnsupportedOperationException("TODO: implement compose");
    }
}

