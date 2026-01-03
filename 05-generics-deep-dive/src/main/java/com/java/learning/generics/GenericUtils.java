package com.java.learning.generics;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility-методы с использованием generics.
 * 
 * TODO: Реализуй все методы
 */
public final class GenericUtils {
    
    private GenericUtils() {
        // Utility class
    }
    
    /**
     * Находит максимальный элемент в коллекции.
     * 
     * @param collection коллекция элементов
     * @param <T> тип элементов, должен быть Comparable
     * @return максимальный элемент
     * @throws java.util.NoSuchElementException если коллекция пуста
     */
    public static <T extends Comparable<? super T>> T max(Collection<T> collection) {
        // TODO: Реализуй метод
        // Подсказка: почему Comparable<? super T>, а не Comparable<T>?
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Находит минимальный элемент в коллекции.
     */
    public static <T extends Comparable<? super T>> T min(Collection<T> collection) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Копирует элементы из исходного списка в целевой.
     * 
     * Демонстрирует PECS: source — producer (extends), dest — consumer (super).
     * 
     * @param source исходный список (читаем из него)
     * @param dest целевой список (пишем в него)
     * @param <T> базовый тип элементов
     */
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Фильтрует список по предикату.
     * 
     * @param list исходный список
     * @param predicate условие фильтрации
     * @param <T> тип элементов
     * @return новый список с отфильтрованными элементами
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        // TODO: Реализуй метод
        // Подсказка: почему Predicate<? super T>?
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразует список элементов.
     * 
     * @param list исходный список
     * @param mapper функция преобразования
     * @param <T> тип исходных элементов
     * @param <R> тип результирующих элементов
     * @return новый список с преобразованными элементами
     */
    public static <T, R> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Инвертирует Map: ключи становятся значениями, значения — ключами.
     * 
     * @param map исходная Map
     * @param <K> тип ключей
     * @param <V> тип значений
     * @return инвертированная Map
     * @throws IllegalArgumentException если есть дублирующиеся значения
     */
    public static <K, V> Map<V, K> invert(Map<K, V> map) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Группирует элементы по ключу.
     * 
     * @param list список элементов
     * @param keyExtractor функция извлечения ключа
     * @param <T> тип элементов
     * @param <K> тип ключа
     * @return Map: ключ -> список элементов с этим ключом
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<? super T, ? extends K> keyExtractor) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Объединяет два списка в список Pair.
     * 
     * @param first первый список
     * @param second второй список
     * @param <A> тип элементов первого списка
     * @param <B> тип элементов второго списка
     * @return список Pair
     */
    public static <A, B> List<Pair<A, B>> zip(List<A> first, List<B> second) {
        // TODO: Реализуй метод
        // Длина результата = минимум из длин входных списков
        throw new UnsupportedOperationException("TODO: implement");
    }
}

