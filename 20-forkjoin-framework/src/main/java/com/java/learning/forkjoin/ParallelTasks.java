package com.java.learning.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.RecursiveAction;

/**
 * Задачи для Fork/Join Framework.
 * 
 * Fork/Join — это механизм для параллельного выполнения divide-and-conquer задач:
 * 1. Разбей задачу на подзадачи
 * 2. Запусти подзадачи параллельно (fork)
 * 3. Дождись результатов (join)
 * 4. Объедини результаты
 * 
 * Work-stealing: незанятые потоки "воруют" задачи у занятых.
 * 
 * TODO: Реализуй все задачи
 */
public class ParallelTasks {
    
    /**
     * Параллельное суммирование массива.
     */
    public static class SumTask extends RecursiveTask<Long> {
        
        private static final int THRESHOLD = 10_000;
        
        private final long[] array;
        private final int start;
        private final int end;
        
        public SumTask(long[] array) {
            this(array, 0, array.length);
        }
        
        private SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Long compute() {
            // TODO: Реализуй
            // 1. Если размер <= THRESHOLD — вычисли напрямую
            // 2. Иначе — раздели на две подзадачи
            // 3. Fork одну, compute другую
            // 4. Join и объедини результаты
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Параллельная сортировка слиянием.
     */
    public static class MergeSortTask extends RecursiveAction {
        
        private static final int THRESHOLD = 1_000;
        
        private final int[] array;
        private final int start;
        private final int end;
        private final int[] temp;
        
        public MergeSortTask(int[] array) {
            this(array, 0, array.length, new int[array.length]);
        }
        
        private MergeSortTask(int[] array, int start, int end, int[] temp) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.temp = temp;
        }
        
        @Override
        protected void compute() {
            // TODO: Реализуй
            // 1. Если размер <= THRESHOLD — используй Arrays.sort
            // 2. Иначе — раздели на две половины
            // 3. Рекурсивно отсортируй каждую (параллельно)
            // 4. Слей отсортированные половины
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        private void merge(int mid) {
            // TODO: Реализуй слияние двух отсортированных половин
            throw new UnsupportedOperationException("TODO: implement merge");
        }
    }
    
    /**
     * Параллельный подсчёт элементов, удовлетворяющих условию.
     */
    public static class CountTask<T> extends RecursiveTask<Long> {
        
        private static final int THRESHOLD = 1_000;
        
        private final T[] array;
        private final int start;
        private final int end;
        private final java.util.function.Predicate<T> predicate;
        
        public CountTask(T[] array, java.util.function.Predicate<T> predicate) {
            this(array, 0, array.length, predicate);
        }
        
        private CountTask(T[] array, int start, int end, java.util.function.Predicate<T> predicate) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.predicate = predicate;
        }
        
        @Override
        protected Long compute() {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

