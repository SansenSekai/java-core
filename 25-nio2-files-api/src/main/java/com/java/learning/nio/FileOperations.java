package com.java.learning.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Операции с файловой системой через NIO.2.
 * 
 * NIO.2 (Java 7+) предоставляет:
 * - Path — современная замена File
 * - Files — утилиты для работы с файлами
 * - WatchService — отслеживание изменений
 * - FileVisitor — обход дерева каталогов
 * 
 * TODO: Реализуй все методы
 */
public class FileOperations {
    
    /**
     * Рекурсивно находит все файлы с заданным расширением.
     * 
     * @param directory начальный каталог
     * @param extension расширение (например, "java")
     * @return список путей к файлам
     */
    public static List<Path> findByExtension(Path directory, String extension) throws IOException {
        // TODO: Реализуй
        // Подсказка: Files.walk или Files.find
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Копирует директорию рекурсивно.
     */
    public static void copyDirectory(Path source, Path target) throws IOException {
        // TODO: Реализуй
        // Подсказка: Files.walkFileTree с SimpleFileVisitor
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Удаляет директорию рекурсивно.
     */
    public static void deleteDirectory(Path directory) throws IOException {
        // TODO: Реализуй
        // Подсказка: нужно сначала удалить содержимое
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Возвращает размер директории (сумма размеров всех файлов).
     */
    public static long getDirectorySize(Path directory) throws IOException {
        // TODO: Реализуй
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Находит дубликаты файлов (по содержимому).
     * 
     * @return Map<hash, список файлов с одинаковым содержимым>
     */
    public static Map<String, List<Path>> findDuplicates(Path directory) throws IOException {
        // TODO: Реализуй
        // Подсказка: вычисли hash каждого файла (например, SHA-256)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Атомарно перемещает файл.
     */
    public static void atomicMove(Path source, Path target) throws IOException {
        // TODO: Реализуй
        // Подсказка: Files.move с ATOMIC_MOVE
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Читает файл построчно с фильтрацией.
     * 
     * Возвращает Stream для ленивой обработки больших файлов.
     */
    public static Stream<String> readLinesFiltered(Path file, java.util.function.Predicate<String> filter) 
            throws IOException {
        // TODO: Реализуй
        // Подсказка: Files.lines
        throw new UnsupportedOperationException("TODO: implement");
    }
}

