package com.java.learning.performance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Benchmark Examples")
class BenchmarkExamplesTest {

    /**
     * Эти тесты проверяют корректность операций,
     * а не их производительность.
     * 
     * Для реального бенчмаркинга нужен JMH.
     */
    
    @Nested
    @DisplayName("String Concatenation")
    class StringConcat {
        
        @Test
        @DisplayName("Все методы дают одинаковый результат")
        void allMethodsShouldProduceSameResult() {
            String a = "Hello";
            String b = " ";
            String c = "World";
            
            String result1 = BenchmarkExamples.StringConcatComparison.concatWithPlus(a, b, c);
            String result2 = BenchmarkExamples.StringConcatComparison.concatWithBuilder(a, b, c);
            String result3 = BenchmarkExamples.StringConcatComparison.concatWithMethod(a, b, c);
            
            assertThat(result1).isEqualTo("Hello World");
            assertThat(result2).isEqualTo("Hello World");
            assertThat(result3).isEqualTo("Hello World");
        }
        
        @Test
        @DisplayName("Пустые строки обрабатываются корректно")
        void shouldHandleEmptyStrings() {
            String result = BenchmarkExamples.StringConcatComparison.concatWithPlus("", "", "");
            assertThat(result).isEmpty();
        }
    }
    
    @Test
    @DisplayName("naiveBenchmark выполняется без ошибок")
    void naiveBenchmarkShouldRun() {
        // Просто проверяем, что код работает
        // Это НЕ реальный бенчмарк!
        assertThatNoException().isThrownBy(BenchmarkExamples::naiveBenchmark);
    }
}

