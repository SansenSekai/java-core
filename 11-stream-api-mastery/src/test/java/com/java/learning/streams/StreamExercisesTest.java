package com.java.learning.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Stream API Exercises")
class StreamExercisesTest {

    @Nested
    @DisplayName("sumOfSquaresOfEvens")
    class SumOfSquares {
        
        @Test
        @DisplayName("Сумма квадратов чётных чисел")
        void shouldSumSquaresOfEvens() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
            // 2^2 + 4^2 + 6^2 = 4 + 16 + 36 = 56
            
            assertThat(StreamExercises.sumOfSquaresOfEvens(numbers)).isEqualTo(56);
        }
        
        @Test
        @DisplayName("Пустой список возвращает 0")
        void shouldReturnZeroForEmptyList() {
            assertThat(StreamExercises.sumOfSquaresOfEvens(List.of())).isZero();
        }
    }
    
    @Nested
    @DisplayName("topThreeLongestWordsUppercased")
    class TopThree {
        
        @Test
        @DisplayName("Три самых длинных слова в верхнем регистре")
        void shouldFindTopThree() {
            List<String> words = List.of("a", "bb", "ccc", "dddd", "eeeee", "ffffff");
            
            List<String> result = StreamExercises.topThreeLongestWordsUppercased(words);
            
            assertThat(result)
                .hasSize(3)
                .containsExactly("DDDD", "EEEEE", "FFFFFF"); // Отсортированы по алфавиту
        }
    }
    
    @Nested
    @DisplayName("groupByLength")
    class GroupByLength {
        
        @Test
        @DisplayName("Группирует строки по длине")
        void shouldGroupByLength() {
            List<String> strings = List.of("a", "b", "cc", "dd", "eee");
            
            Map<Integer, List<String>> result = StreamExercises.groupByLength(strings);
            
            assertThat(result).containsEntry(1, List.of("a", "b"));
            assertThat(result).containsEntry(2, List.of("cc", "dd"));
            assertThat(result).containsEntry(3, List.of("eee"));
        }
    }
    
    @Nested
    @DisplayName("secondMostFrequentChar")
    class SecondMostFrequent {
        
        @Test
        @DisplayName("Находит второй по частоте символ")
        void shouldFindSecondMostFrequent() {
            String s = "aabbbcccc"; // c=4, b=3, a=2
            
            Optional<Character> result = StreamExercises.secondMostFrequentChar(s);
            
            assertThat(result).contains('b');
        }
        
        @Test
        @DisplayName("Пустой результат для короткой строки")
        void shouldReturnEmptyForShortString() {
            assertThat(StreamExercises.secondMostFrequentChar("a")).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("flattenAndDistinct")
    class FlattenAndDistinct {
        
        @Test
        @DisplayName("Сплющивает вложенные списки и удаляет дубликаты")
        void shouldFlattenAndRemoveDuplicates() {
            List<List<Integer>> nested = List.of(
                List.of(1, 2, 3),
                List.of(2, 3, 4),
                List.of(4, 5)
            );
            
            List<Integer> result = StreamExercises.flattenAndDistinct(nested);
            
            assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4, 5);
        }
    }
    
    @Nested
    @DisplayName("findAnagrams")
    class Anagrams {
        
        @Test
        @DisplayName("Находит все анаграммы")
        void shouldFindAnagrams() {
            List<String> candidates = List.of("silent", "listen", "hello", "enlist", "world");
            
            List<String> result = StreamExercises.findAnagrams("listen", candidates);
            
            assertThat(result).containsExactlyInAnyOrder("silent", "listen", "enlist");
        }
    }
    
    @Nested
    @DisplayName("fibonacciStream")
    class Fibonacci {
        
        @Test
        @DisplayName("Генерирует числа Фибоначчи")
        void shouldGenerateFibonacci() {
            List<Long> first10 = StreamExercises.fibonacciStream()
                .limit(10)
                .toList();
            
            assertThat(first10).containsExactly(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L);
        }
    }
    
    @Nested
    @DisplayName("partition")
    class Partition {
        
        @Test
        @DisplayName("Разделяет список по предикату")
        void shouldPartition() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
            
            Map<Boolean, List<Integer>> result = StreamExercises.partition(numbers, n -> n % 2 == 0);
            
            assertThat(result.get(true)).containsExactly(2, 4, 6);
            assertThat(result.get(false)).containsExactly(1, 3, 5);
        }
    }
    
    @Nested
    @DisplayName("product")
    class Product {
        
        @Test
        @DisplayName("Вычисляет произведение элементов")
        void shouldComputeProduct() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);
            
            BigInteger result = StreamExercises.product(numbers);
            
            assertThat(result).isEqualTo(BigInteger.valueOf(120));
        }
    }
}

