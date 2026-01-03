package com.java.learning.exceptions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

/**
 * Процессор файлов с правильной обработкой ресурсов.
 * 
 * Демонстрирует:
 * - try-with-resources
 * - Обработка нескольких ресурсов
 * - Suppressed exceptions
 * 
 * TODO: Реализуй этот класс
 */
public class FileProcessor {
    
    /**
     * Читает файл и применяет функцию к каждой строке.
     * 
     * @param filePath путь к файлу
     * @param lineProcessor функция для обработки каждой строки
     * @return список результатов обработки
     * @throws FileProcessingException если произошла ошибка при чтении
     */
    public <T> List<T> processFile(Path filePath, Function<String, T> lineProcessor) {
        // TODO: Реализуй метод с использованием try-with-resources
        // 
        // Подсказка: используй BufferedReader для чтения файла
        // Не забудь обернуть IOException в FileProcessingException
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Копирует файл с обработкой каждой строки.
     * 
     * @param source исходный файл
     * @param destination целевой файл
     * @param lineTransformer функция трансформации строки
     * @throws FileProcessingException если произошла ошибка
     */
    public void copyWithTransform(
            Path source, 
            Path destination, 
            Function<String, String> lineTransformer) {
        // TODO: Реализуй метод
        // Используй try-with-resources с двумя ресурсами:
        // - BufferedReader для чтения
        // - BufferedWriter для записи
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Демонстрирует подавленные исключения (suppressed exceptions).
     * 
     * Когда и основной код, и close() бросают исключения,
     * исключение из close() добавляется как suppressed.
     */
    public void demonstrateSuppressedExceptions() {
        // TODO: Реализуй метод
        // Создай ресурс, который бросает исключение при close()
        // Брось исключение в теле try
        // Покажи, как получить suppressed exceptions
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Исключение обработки файла.
     */
    public static class FileProcessingException extends RuntimeException {
        
        private final Path filePath;
        
        public FileProcessingException(String message, Path filePath, Throwable cause) {
            super(message + " [file: " + filePath + "]", cause);
            this.filePath = filePath;
        }
        
        public Path getFilePath() {
            return filePath;
        }
    }
}

