package com.java.learning.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Function Utils")
class FunctionUtilsTest {

    @Nested
    @DisplayName("Каррирование")
    class Currying {
        
        @Test
        @DisplayName("curry преобразует BiFunction в цепочку Function")
        void shouldCurryBiFunction() {
            BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
            
            Function<Integer, Function<Integer, Integer>> curriedAdd = FunctionUtils.curry(add);
            
            Function<Integer, Integer> add5 = curriedAdd.apply(5);
            assertThat(add5.apply(3)).isEqualTo(8);
            assertThat(add5.apply(10)).isEqualTo(15);
        }
        
        @Test
        @DisplayName("uncurry восстанавливает BiFunction")
        void shouldUncurryCurriedFunction() {
            Function<String, Function<Integer, String>> curriedRepeat = 
                s -> n -> s.repeat(n);
            
            BiFunction<String, Integer, String> repeat = FunctionUtils.uncurry(curriedRepeat);
            
            assertThat(repeat.apply("ab", 3)).isEqualTo("ababab");
        }
    }
    
    @Nested
    @DisplayName("Частичное применение")
    class PartialApplication {
        
        @Test
        @DisplayName("partial фиксирует первый аргумент")
        void shouldPartiallyApply() {
            BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
            
            Function<Integer, String> repeatHello = FunctionUtils.partial(repeat, "Hi");
            
            assertThat(repeatHello.apply(3)).isEqualTo("HiHiHi");
        }
    }
    
    @Nested
    @DisplayName("Мемоизация")
    class Memoization {
        
        @Test
        @DisplayName("memoize кэширует результаты")
        void shouldCacheResults() {
            AtomicInteger callCount = new AtomicInteger(0);
            
            Function<Integer, Integer> expensive = n -> {
                callCount.incrementAndGet();
                return n * n;
            };
            
            Function<Integer, Integer> memoized = FunctionUtils.memoize(expensive);
            
            assertThat(memoized.apply(5)).isEqualTo(25);
            assertThat(memoized.apply(5)).isEqualTo(25);
            assertThat(memoized.apply(5)).isEqualTo(25);
            
            assertThat(callCount.get()).isEqualTo(1); // Вызвано только один раз
        }
        
        @Test
        @DisplayName("memoize работает для разных аргументов")
        void shouldCacheDifferentArguments() {
            AtomicInteger callCount = new AtomicInteger(0);
            
            Function<Integer, Integer> expensive = n -> {
                callCount.incrementAndGet();
                return n * 2;
            };
            
            Function<Integer, Integer> memoized = FunctionUtils.memoize(expensive);
            
            memoized.apply(1);
            memoized.apply(2);
            memoized.apply(1);
            memoized.apply(2);
            
            assertThat(callCount.get()).isEqualTo(2); // По одному разу для каждого аргумента
        }
    }
    
    @Nested
    @DisplayName("Ленивая инициализация")
    class LazyInitialization {
        
        @Test
        @DisplayName("lazy вычисляет значение только один раз")
        void shouldComputeOnlyOnce() {
            AtomicInteger callCount = new AtomicInteger(0);
            
            Supplier<String> lazyValue = FunctionUtils.lazy(() -> {
                callCount.incrementAndGet();
                return "computed";
            });
            
            assertThat(lazyValue.get()).isEqualTo("computed");
            assertThat(lazyValue.get()).isEqualTo("computed");
            assertThat(lazyValue.get()).isEqualTo("computed");
            
            assertThat(callCount.get()).isEqualTo(1);
        }
    }
    
    @Nested
    @DisplayName("Композиция")
    class Composition {
        
        @Test
        @DisplayName("compose применяет функции последовательно")
        void shouldComposeMultipleFunctions() {
            Function<Integer, Integer> addOne = x -> x + 1;
            Function<Integer, Integer> multiplyTwo = x -> x * 2;
            Function<Integer, Integer> square = x -> x * x;
            
            Function<Integer, Integer> composed = FunctionUtils.compose(addOne, multiplyTwo, square);
            
            // (((3 + 1) * 2) ^ 2) = ((4 * 2) ^ 2) = (8 ^ 2) = 64
            assertThat(composed.apply(3)).isEqualTo(64);
        }
    }
    
    @Nested
    @DisplayName("TriFunction")
    class TriFunctionTest {
        
        @Test
        @DisplayName("TriFunction принимает три аргумента")
        void shouldAcceptThreeArguments() {
            TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
            
            assertThat(sum3.apply(1, 2, 3)).isEqualTo(6);
        }
        
        @Test
        @DisplayName("andThen применяет функцию к результату")
        void shouldApplyAndThen() {
            TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
            TriFunction<Integer, Integer, Integer, String> sum3ToString = 
                sum3.andThen(n -> "Result: " + n);
            
            assertThat(sum3ToString.apply(1, 2, 3)).isEqualTo("Result: 6");
        }
    }
}

