package com.java.learning.threads;

/**
 * Демонстрация создания потоков разными способами.
 * 
 * TODO: Реализуй все примеры
 */
public class SimpleThread {
    
    /**
     * Способ 1: Наследование от Thread.
     */
    public static class CountingThread extends Thread {
        
        private final String name;
        private final int count;
        
        public CountingThread(String name, int count) {
            this.name = name;
            this.count = count;
        }
        
        @Override
        public void run() {
            // TODO: Реализуй счёт от 1 до count с паузами
            // Проверяй Thread.interrupted() для корректного прерывания
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Способ 2: Реализация Runnable.
     */
    public static class CountingRunnable implements Runnable {
        
        private final String name;
        private final int count;
        
        public CountingRunnable(String name, int count) {
            this.name = name;
            this.count = count;
        }
        
        @Override
        public void run() {
            // TODO: Реализуй аналогично CountingThread
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Демонстрация прерывания потока.
     */
    public static void demonstrateInterruption() {
        // TODO: Создай поток, который делает длительную работу
        // Прерви его через 2 секунды
        // Покажи, как поток реагирует на прерывание
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Демонстрация join().
     */
    public static void demonstrateJoin() {
        // TODO: Создай несколько потоков
        // Дождись завершения всех с помощью join()
        throw new UnsupportedOperationException("TODO: implement");
    }
}

