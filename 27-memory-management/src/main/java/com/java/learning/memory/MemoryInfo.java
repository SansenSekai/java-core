package com.java.learning.memory;

import java.lang.management.*;
import java.util.List;

/**
 * Утилиты для анализа памяти JVM.
 * 
 * Области памяти JVM:
 * - Heap: объекты (Young + Old generation)
 * - Metaspace: метаданные классов (до Java 8 — PermGen)
 * - Stack: локальные переменные, вызовы методов
 * - Native memory: JNI, буферы
 * 
 * Сборщики мусора:
 * - Serial GC: однопоточный, для маленьких heap
 * - Parallel GC: многопоточный, throughput
 * - G1 GC: балансирует latency и throughput (default)
 * - ZGC: низкие паузы (<10ms), большие heap
 * - Shenandoah: низкие паузы, concurrent
 * 
 * TODO: Изучи утилиты и пойми, как мониторить память
 */
public class MemoryInfo {
    
    private static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    private static final Runtime runtime = Runtime.getRuntime();
    
    /**
     * Возвращает информацию о heap памяти.
     */
    public static HeapInfo getHeapInfo() {
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        return new HeapInfo(
            heapUsage.getInit(),
            heapUsage.getUsed(),
            heapUsage.getCommitted(),
            heapUsage.getMax()
        );
    }
    
    /**
     * Возвращает информацию о non-heap памяти (Metaspace).
     */
    public static HeapInfo getNonHeapInfo() {
        MemoryUsage nonHeapUsage = memoryMXBean.getNonHeapMemoryUsage();
        return new HeapInfo(
            nonHeapUsage.getInit(),
            nonHeapUsage.getUsed(),
            nonHeapUsage.getCommitted(),
            nonHeapUsage.getMax()
        );
    }
    
    /**
     * Возвращает информацию о всех memory pools.
     */
    public static void printMemoryPools() {
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            System.out.println(pool.getName() + ": " + pool.getUsage());
        }
    }
    
    /**
     * Запрашивает GC (не гарантирует выполнение!).
     */
    public static void requestGC() {
        System.gc();
    }
    
    /**
     * Возвращает количество GC коллекций.
     */
    public static long getGCCount() {
        long count = 0;
        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            count += gc.getCollectionCount();
        }
        return count;
    }
    
    /**
     * Информация о heap памяти.
     */
    public record HeapInfo(long init, long used, long committed, long max) {
        
        public double usedPercent() {
            return max > 0 ? (double) used / max * 100 : 0;
        }
        
        @Override
        public String toString() {
            return String.format(
                "Used: %d MB / %d MB (%.1f%%)",
                used / 1024 / 1024,
                max / 1024 / 1024,
                usedPercent()
            );
        }
    }
}

