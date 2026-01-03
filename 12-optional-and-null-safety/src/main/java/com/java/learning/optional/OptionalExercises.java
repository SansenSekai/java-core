package com.java.learning.optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Практика работы с Optional.
 * 
 * Правила:
 * - Никогда не вызывай get() без проверки isPresent()
 * - Лучше используй orElse(), orElseGet(), orElseThrow()
 * - Используй map(), flatMap(), filter() вместо императивных проверок
 * 
 * TODO: Реализуй все методы
 */
public class OptionalExercises {
    
    /**
     * Возвращает первый непустой Optional из списка.
     * 
     * Подобно COALESCE в SQL.
     */
    @SafeVarargs
    public static <T> Optional<T> firstPresent(Optional<T>... optionals) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Применяет цепочку Optional-функций.
     * 
     * Если любая функция вернёт empty, весь результат — empty.
     */
    @SafeVarargs
    public static <T> Optional<T> chain(T initial, Function<T, Optional<T>>... functions) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * "Unzip" Optional пары в пару Optional'ов.
     */
    public static <A, B> Pair<Optional<A>, Optional<B>> unzip(Optional<Pair<A, B>> optionalPair) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * "Zip" двух Optional'ов в Optional пары.
     * 
     * Возвращает present только если оба Optional present.
     */
    public static <A, B> Optional<Pair<A, B>> zip(Optional<A> optA, Optional<B> optB) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразует Optional<Optional<T>> в Optional<T>.
     */
    public static <T> Optional<T> flatten(Optional<Optional<T>> nested) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает значение или выбрасывает исключение с кастомным сообщением.
     */
    public static <T, X extends Throwable> T getOrThrow(
            Optional<T> optional, 
            Supplier<? extends X> exceptionSupplier) throws X {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Фильтрует список, оставляя только present значения.
     */
    public static <T> List<T> filterPresent(List<Optional<T>> optionals) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Простой класс пары для упражнений.
     */
    public record Pair<A, B>(A first, B second) {}
}

