package com.java.learning.nio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("NIO.2 File Operations")
class FileOperationsTest {

    @TempDir
    Path tempDir;
    
    @Nested
    @DisplayName("findByExtension")
    class FindByExtension {
        
        @Test
        @DisplayName("Находит файлы с заданным расширением")
        void shouldFindByExtension() throws IOException {
            Files.createFile(tempDir.resolve("file1.java"));
            Files.createFile(tempDir.resolve("file2.java"));
            Files.createFile(tempDir.resolve("file3.txt"));
            Path subDir = Files.createDirectory(tempDir.resolve("sub"));
            Files.createFile(subDir.resolve("file4.java"));
            
            List<Path> result = FileOperations.findByExtension(tempDir, "java");
            
            assertThat(result).hasSize(3);
            assertThat(result).allMatch(p -> p.toString().endsWith(".java"));
        }
        
        @Test
        @DisplayName("Возвращает пустой список если не найдено")
        void shouldReturnEmptyIfNotFound() throws IOException {
            Files.createFile(tempDir.resolve("file.txt"));
            
            List<Path> result = FileOperations.findByExtension(tempDir, "java");
            
            assertThat(result).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("copyDirectory")
    class CopyDirectory {
        
        @Test
        @DisplayName("Копирует директорию рекурсивно")
        void shouldCopyRecursively() throws IOException {
            // Создаём структуру
            Path source = Files.createDirectory(tempDir.resolve("source"));
            Files.createFile(source.resolve("file1.txt"));
            Path sub = Files.createDirectory(source.resolve("subdir"));
            Files.createFile(sub.resolve("file2.txt"));
            
            Path target = tempDir.resolve("target");
            
            FileOperations.copyDirectory(source, target);
            
            assertThat(target).exists();
            assertThat(target.resolve("file1.txt")).exists();
            assertThat(target.resolve("subdir/file2.txt")).exists();
        }
    }
    
    @Nested
    @DisplayName("deleteDirectory")
    class DeleteDirectory {
        
        @Test
        @DisplayName("Удаляет директорию рекурсивно")
        void shouldDeleteRecursively() throws IOException {
            Path dir = Files.createDirectory(tempDir.resolve("toDelete"));
            Files.createFile(dir.resolve("file.txt"));
            Path sub = Files.createDirectory(dir.resolve("sub"));
            Files.createFile(sub.resolve("nested.txt"));
            
            FileOperations.deleteDirectory(dir);
            
            assertThat(dir).doesNotExist();
        }
    }
    
    @Nested
    @DisplayName("getDirectorySize")
    class GetDirectorySize {
        
        @Test
        @DisplayName("Вычисляет размер директории")
        void shouldCalculateSize() throws IOException {
            Path dir = Files.createDirectory(tempDir.resolve("sized"));
            Files.write(dir.resolve("file1.txt"), new byte[100]);
            Files.write(dir.resolve("file2.txt"), new byte[200]);
            Path sub = Files.createDirectory(dir.resolve("sub"));
            Files.write(sub.resolve("file3.txt"), new byte[300]);
            
            long size = FileOperations.getDirectorySize(dir);
            
            assertThat(size).isEqualTo(600);
        }
    }
    
    @Nested
    @DisplayName("atomicMove")
    class AtomicMove {
        
        @Test
        @DisplayName("Атомарно перемещает файл")
        void shouldMoveAtomically() throws IOException {
            Path source = Files.createFile(tempDir.resolve("source.txt"));
            Files.writeString(source, "content");
            Path target = tempDir.resolve("target.txt");
            
            FileOperations.atomicMove(source, target);
            
            assertThat(source).doesNotExist();
            assertThat(target).exists();
            assertThat(Files.readString(target)).isEqualTo("content");
        }
    }
    
    @Nested
    @DisplayName("readLinesFiltered")
    class ReadLinesFiltered {
        
        @Test
        @DisplayName("Читает и фильтрует строки")
        void shouldFilterLines() throws IOException {
            Path file = Files.createFile(tempDir.resolve("lines.txt"));
            Files.writeString(file, "hello\nworld\nfoo\nbar\nhello again");
            
            try (Stream<String> lines = FileOperations.readLinesFiltered(
                    file, line -> line.contains("hello"))) {
                List<String> result = lines.toList();
                
                assertThat(result).containsExactly("hello", "hello again");
            }
        }
    }
}

