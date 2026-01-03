package com.java.learning.io;

import java.io.*;

/**
 * Упражнения на классический Java I/O.
 * 
 * Иерархия:
 * - InputStream/OutputStream — байтовые потоки
 * - Reader/Writer — символьные потоки
 * - Buffered* — буферизация для производительности
 * - Data* — примитивные типы
 * - Object* — сериализация
 * 
 * TODO: Реализуй все методы
 */
public class IOExercises {
    
    /**
     * Копирует файл с буферизацией.
     * 
     * Используй try-with-resources!
     */
    public static void copyFile(String source, String destination) throws IOException {
        // TODO: Реализуй
        // Подсказка: BufferedInputStream + BufferedOutputStream
        // Читай блоками (например, 8KB)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Читает текстовый файл построчно.
     * 
     * @return содержимое файла
     */
    public static String readTextFile(String filename) throws IOException {
        // TODO: Реализуй
        // Подсказка: BufferedReader.readLine()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Записывает текст в файл.
     */
    public static void writeTextFile(String filename, String content) throws IOException {
        // TODO: Реализуй
        // Подсказка: BufferedWriter или PrintWriter
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Подсчитывает количество строк в файле.
     */
    public static long countLines(String filename) throws IOException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Декоратор для подсчёта прочитанных байтов.
     * 
     * Пример паттерна Decorator в I/O.
     */
    public static class CountingInputStream extends FilterInputStream {
        
        private long bytesRead = 0;
        
        public CountingInputStream(InputStream in) {
            super(in);
        }
        
        @Override
        public int read() throws IOException {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public long getBytesRead() {
            return bytesRead;
        }
    }
}

