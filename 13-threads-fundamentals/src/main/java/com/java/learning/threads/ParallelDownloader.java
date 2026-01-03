package com.java.learning.threads;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * Параллельный загрузчик файлов.
 * 
 * Демонстрирует:
 * - Создание и управление потоками
 * - Ограничение параллелизма
 * - Обработку прерываний
 * - Отслеживание прогресса
 * 
 * TODO: Реализуй все методы
 */
public class ParallelDownloader {
    
    private final int maxConcurrent;
    private volatile boolean cancelled = false;
    
    public ParallelDownloader(int maxConcurrent) {
        if (maxConcurrent <= 0) {
            throw new IllegalArgumentException("maxConcurrent must be positive");
        }
        this.maxConcurrent = maxConcurrent;
    }
    
    /**
     * Результат загрузки.
     */
    public record DownloadResult(String url, boolean success, String error) {
        public static DownloadResult success(String url) {
            return new DownloadResult(url, true, null);
        }
        
        public static DownloadResult failure(String url, String error) {
            return new DownloadResult(url, false, error);
        }
    }
    
    /**
     * Загружает файлы параллельно.
     * 
     * @param urls список URL для загрузки
     * @param downloader функция загрузки (симуляция)
     * @param progressCallback callback для отслеживания прогресса
     * @return список результатов
     * @throws InterruptedException если загрузка прервана
     */
    public List<DownloadResult> downloadAll(
            List<String> urls,
            Consumer<String> downloader,
            Consumer<DownloadResult> progressCallback) throws InterruptedException {
        // TODO: Реализуй параллельную загрузку
        // 1. Создай потоки для загрузки (не более maxConcurrent одновременно)
        // 2. Каждый поток загружает URL и сообщает результат
        // 3. Обработай прерывания корректно
        // Подсказка: используй Semaphore для ограничения параллелизма
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Отменяет все загрузки.
     */
    public void cancel() {
        this.cancelled = true;
        // TODO: Прерви все активные потоки
    }
    
    /**
     * Проверяет, была ли загрузка отменена.
     */
    public boolean isCancelled() {
        return cancelled;
    }
}

