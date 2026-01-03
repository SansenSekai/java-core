package com.java.learning.performance;

/**
 * Примеры для бенчмаркинга с JMH.
 * 
 * JMH (Java Microbenchmark Harness) — официальный инструмент OpenJDK
 * для микробенчмаркинга.
 * 
 * Почему нужен JMH:
 * - JIT-компилятор может удалить "мёртвый" код
 * - Warmup: JIT не сразу оптимизирует
 * - Измерение времени имеет overhead
 * 
 * Типичные ошибки бенчмарков:
 * 1. Dead code elimination — JIT удаляет неиспользуемые результаты
 * 2. Constant folding — JIT вычисляет константы заранее
 * 3. Loop unrolling — JIT разворачивает циклы
 * 4. Недостаточный warmup
 * 
 * TODO: Изучи примеры и напиши свои бенчмарки
 * 
 * Для запуска JMH нужно добавить зависимости в pom.xml
 * и использовать maven-shade-plugin.
 */
public class BenchmarkExamples {
    
    // Пример структуры JMH бенчмарка (для справки):
    // 
    // @Benchmark
    // @BenchmarkMode(Mode.Throughput)
    // @OutputTimeUnit(TimeUnit.MILLISECONDS)
    // @Warmup(iterations = 5, time = 1)
    // @Measurement(iterations = 10, time = 1)
    // @Fork(2)
    // public void testStringConcat(Blackhole bh) {
    //     String result = "Hello" + " " + "World";
    //     bh.consume(result);  // Предотвращает dead code elimination
    // }
    
    /**
     * Сравнение String конкатенации.
     */
    public static class StringConcatComparison {
        
        /**
         * Конкатенация через +.
         * 
         * До Java 9: создаёт StringBuilder
         * Java 9+: invokedynamic, более эффективно
         */
        public static String concatWithPlus(String a, String b, String c) {
            return a + b + c;
        }
        
        /**
         * Явный StringBuilder.
         */
        public static String concatWithBuilder(String a, String b, String c) {
            return new StringBuilder()
                .append(a)
                .append(b)
                .append(c)
                .toString();
        }
        
        /**
         * String.concat() — создаёт новую строку каждый раз.
         */
        public static String concatWithMethod(String a, String b, String c) {
            return a.concat(b).concat(c);
        }
    }
    
    /**
     * Сравнение коллекций.
     */
    public static class CollectionComparison {
        
        // TODO: Добавь бенчмарки для:
        // - ArrayList vs LinkedList (add, get, iterate)
        // - HashMap vs TreeMap (put, get)
        // - HashSet vs TreeSet (add, contains)
    }
    
    /**
     * Демонстрация проблем измерения без JMH.
     */
    public static void naiveBenchmark() {
        // ПЛОХО: наивный бенчмарк
        long start = System.nanoTime();
        
        int sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println("Sum: " + sum);  // Без этого JIT может удалить весь цикл!
        
        // Проблемы:
        // 1. JIT не успел оптимизировать (нужен warmup)
        // 2. System.nanoTime() имеет overhead
        // 3. Один запуск не репрезентативен
    }
}

