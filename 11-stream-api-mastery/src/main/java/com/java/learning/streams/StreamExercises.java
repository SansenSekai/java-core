package com.java.learning.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Практические упражнения на Stream API.
 * 
 * TODO: Реализуй все методы, используя Stream API
 * 
 * Правила:
 * - Никаких циклов (for, while)
 * - Никаких изменяемых переменных вне стримов
 * - Только Stream API!
 */
public class StreamExercises {
    
    /**
     * Возвращает сумму квадратов всех чётных чисел.
     */
    public static long sumOfSquaresOfEvens(List<Integer> numbers) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Находит три самых длинных слова, переводит в верхний регистр и сортирует по алфавиту.
     */
    public static List<String> topThreeLongestWordsUppercased(List<String> words) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Группирует строки по их длине.
     * 
     * @return Map<длина, список строк с этой длиной>
     */
    public static Map<Integer, List<String>> groupByLength(List<String> strings) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Находит второй по частоте символ в строке.
     * 
     * @return символ или Optional.empty(), если строка слишком короткая
     */
    public static Optional<Character> secondMostFrequentChar(String s) {
        // TODO: Реализуй
        // Подсказка: Character::charValue, Collectors.groupingBy, Collectors.counting
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * "Сплющивает" вложенные списки в один список уникальных элементов.
     */
    public static <T> List<T> flattenAndDistinct(List<List<T>> nestedLists) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Вычисляет произведение всех элементов (внимание на переполнение!).
     * Использует BigInteger для безопасности.
     */
    public static java.math.BigInteger product(List<Integer> numbers) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Находит все анаграммы данного слова в списке.
     * 
     * Анаграмма — слово из тех же букв.
     * Например, "listen" и "silent" — анаграммы.
     */
    public static List<String> findAnagrams(String word, List<String> candidates) {
        // TODO: Реализуй
        // Подсказка: отсортированные буквы слов-анаграмм одинаковы
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт бесконечный поток чисел Фибоначчи.
     * 
     * 0, 1, 1, 2, 3, 5, 8, 13, ...
     */
    public static Stream<Long> fibonacciStream() {
        // TODO: Реализуй
        // Подсказка: Stream.iterate с парами значений
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Параллельно обрабатывает список и возвращает первый подходящий элемент.
     * 
     * Важно: результат должен быть детерминированным (первый по порядку в списке)!
     */
    public static <T> Optional<T> findFirstParallel(List<T> items, java.util.function.Predicate<T> predicate) {
        // TODO: Реализуй
        // Подсказка: findFirst() сохраняет порядок даже в параллельных стримах
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Партиционирует список на две группы по предикату.
     * 
     * @return Map с ключами true/false
     */
    public static <T> Map<Boolean, List<T>> partition(List<T> items, java.util.function.Predicate<T> predicate) {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
}

