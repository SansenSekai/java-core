package com.java.learning.forkjoin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Fork/Join Tasks")
class ParallelTasksTest {

    private final ForkJoinPool pool = ForkJoinPool.commonPool();
    
    @Nested
    @DisplayName("SumTask")
    class SumTaskTest {
        
        @Test
        @DisplayName("Корректно суммирует массив")
        void shouldSumArray() {
            long[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            
            long result = pool.invoke(new ParallelTasks.SumTask(array));
            
            assertThat(result).isEqualTo(55);
        }
        
        @Test
        @DisplayName("Работает с большим массивом")
        void shouldHandleLargeArray() {
            int size = 1_000_000;
            long[] array = new long[size];
            for (int i = 0; i < size; i++) {
                array[i] = i + 1;
            }
            
            long result = pool.invoke(new ParallelTasks.SumTask(array));
            
            // Сумма 1..n = n*(n+1)/2
            long expected = (long) size * (size + 1) / 2;
            assertThat(result).isEqualTo(expected);
        }
        
        @Test
        @DisplayName("Работает с пустым массивом")
        void shouldHandleEmptyArray() {
            long[] array = {};
            
            long result = pool.invoke(new ParallelTasks.SumTask(array));
            
            assertThat(result).isZero();
        }
    }
    
    @Nested
    @DisplayName("MergeSortTask")
    class MergeSortTaskTest {
        
        @Test
        @DisplayName("Корректно сортирует массив")
        void shouldSortArray() {
            int[] array = {5, 2, 8, 1, 9, 3, 7, 4, 6};
            
            pool.invoke(new ParallelTasks.MergeSortTask(array));
            
            assertThat(array).isSorted();
        }
        
        @Test
        @DisplayName("Работает с большим массивом")
        void shouldSortLargeArray() {
            int size = 100_000;
            int[] array = new Random(42).ints(size).toArray();
            int[] expected = array.clone();
            Arrays.sort(expected);
            
            pool.invoke(new ParallelTasks.MergeSortTask(array));
            
            assertThat(array).isEqualTo(expected);
        }
        
        @Test
        @DisplayName("Работает с уже отсортированным массивом")
        void shouldHandleSortedArray() {
            int[] array = {1, 2, 3, 4, 5};
            
            pool.invoke(new ParallelTasks.MergeSortTask(array));
            
            assertThat(array).containsExactly(1, 2, 3, 4, 5);
        }
        
        @Test
        @DisplayName("Работает с обратно отсортированным массивом")
        void shouldHandleReverseSortedArray() {
            int[] array = {5, 4, 3, 2, 1};
            
            pool.invoke(new ParallelTasks.MergeSortTask(array));
            
            assertThat(array).containsExactly(1, 2, 3, 4, 5);
        }
    }
    
    @Nested
    @DisplayName("CountTask")
    class CountTaskTest {
        
        @Test
        @DisplayName("Подсчитывает элементы по условию")
        void shouldCountMatchingElements() {
            Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            
            long count = pool.invoke(
                new ParallelTasks.CountTask<>(array, n -> n % 2 == 0)
            );
            
            assertThat(count).isEqualTo(5); // 2, 4, 6, 8, 10
        }
        
        @Test
        @DisplayName("Возвращает 0 если ничего не найдено")
        void shouldReturnZeroIfNoMatch() {
            Integer[] array = {1, 3, 5, 7, 9};
            
            long count = pool.invoke(
                new ParallelTasks.CountTask<>(array, n -> n % 2 == 0)
            );
            
            assertThat(count).isZero();
        }
    }
}

