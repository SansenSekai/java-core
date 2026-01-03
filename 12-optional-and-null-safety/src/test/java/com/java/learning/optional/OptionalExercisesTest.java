package com.java.learning.optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Optional Exercises")
class OptionalExercisesTest {

    @Nested
    @DisplayName("firstPresent")
    class FirstPresent {
        
        @Test
        @DisplayName("Возвращает первый непустой Optional")
        void shouldReturnFirstPresent() {
            Optional<String> result = OptionalExercises.firstPresent(
                Optional.empty(),
                Optional.empty(),
                Optional.of("found"),
                Optional.of("ignored")
            );
            
            assertThat(result).contains("found");
        }
        
        @Test
        @DisplayName("Возвращает empty если все пустые")
        void shouldReturnEmptyIfAllEmpty() {
            Optional<String> result = OptionalExercises.firstPresent(
                Optional.empty(),
                Optional.empty()
            );
            
            assertThat(result).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("zip и unzip")
    class ZipUnzip {
        
        @Test
        @DisplayName("zip объединяет два Optional")
        void shouldZipTwoOptionals() {
            var result = OptionalExercises.zip(
                Optional.of("hello"),
                Optional.of(42)
            );
            
            assertThat(result).isPresent();
            assertThat(result.get().first()).isEqualTo("hello");
            assertThat(result.get().second()).isEqualTo(42);
        }
        
        @Test
        @DisplayName("zip возвращает empty если один из Optional пуст")
        void shouldReturnEmptyIfOneIsEmpty() {
            var result = OptionalExercises.zip(
                Optional.of("hello"),
                Optional.empty()
            );
            
            assertThat(result).isEmpty();
        }
        
        @Test
        @DisplayName("unzip разбивает Optional пары")
        void shouldUnzipOptionalPair() {
            var pair = new OptionalExercises.Pair<>("hello", 42);
            var result = OptionalExercises.unzip(Optional.of(pair));
            
            assertThat(result.first()).contains("hello");
            assertThat(result.second()).contains(42);
        }
    }
    
    @Nested
    @DisplayName("flatten")
    class Flatten {
        
        @Test
        @DisplayName("flatten распаковывает вложенный Optional")
        void shouldFlatten() {
            Optional<Optional<String>> nested = Optional.of(Optional.of("value"));
            
            Optional<String> result = OptionalExercises.flatten(nested);
            
            assertThat(result).contains("value");
        }
        
        @Test
        @DisplayName("flatten возвращает empty для вложенного empty")
        void shouldReturnEmptyForNestedEmpty() {
            Optional<Optional<String>> nested = Optional.of(Optional.empty());
            
            Optional<String> result = OptionalExercises.flatten(nested);
            
            assertThat(result).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("filterPresent")
    class FilterPresent {
        
        @Test
        @DisplayName("Фильтрует список Optional, оставляя только present")
        void shouldFilterPresent() {
            List<Optional<String>> list = List.of(
                Optional.of("a"),
                Optional.empty(),
                Optional.of("b"),
                Optional.empty(),
                Optional.of("c")
            );
            
            List<String> result = OptionalExercises.filterPresent(list);
            
            assertThat(result).containsExactly("a", "b", "c");
        }
    }
    
    @Nested
    @DisplayName("chain")
    class Chain {
        
        @Test
        @DisplayName("Применяет цепочку функций")
        void shouldChainFunctions() {
            Optional<Integer> result = OptionalExercises.chain(
                1,
                n -> Optional.of(n + 1),
                n -> Optional.of(n * 2),
                n -> Optional.of(n + 10)
            );
            
            // 1 -> 2 -> 4 -> 14
            assertThat(result).contains(14);
        }
        
        @Test
        @DisplayName("Возвращает empty если любая функция возвращает empty")
        void shouldReturnEmptyIfAnyReturnsEmpty() {
            Optional<Integer> result = OptionalExercises.chain(
                1,
                n -> Optional.of(n + 1),
                n -> Optional.empty(),
                n -> Optional.of(n * 100)
            );
            
            assertThat(result).isEmpty();
        }
    }
}

