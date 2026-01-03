package com.java.learning.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

@DisplayName("I/O Exercises")
class IOExercisesTest {

    @TempDir
    Path tempDir;
    
    @Nested
    @DisplayName("copyFile")
    class CopyFile {
        
        @Test
        @DisplayName("Копирует файл")
        void shouldCopyFile() throws IOException {
            Path source = tempDir.resolve("source.txt");
            Path target = tempDir.resolve("target.txt");
            Files.writeString(source, "Hello, World!");
            
            IOExercises.copyFile(source.toString(), target.toString());
            
            assertThat(target).exists();
            assertThat(Files.readString(target)).isEqualTo("Hello, World!");
        }
        
        @Test
        @DisplayName("Копирует большой файл")
        void shouldCopyLargeFile() throws IOException {
            Path source = tempDir.resolve("large.bin");
            Path target = tempDir.resolve("large_copy.bin");
            byte[] data = new byte[1_000_000];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (i % 256);
            }
            Files.write(source, data);
            
            IOExercises.copyFile(source.toString(), target.toString());
            
            assertThat(target).exists();
            assertThat(Files.readAllBytes(target)).isEqualTo(data);
        }
    }
    
    @Nested
    @DisplayName("readTextFile / writeTextFile")
    class ReadWriteText {
        
        @Test
        @DisplayName("Читает текстовый файл")
        void shouldReadTextFile() throws IOException {
            Path file = tempDir.resolve("test.txt");
            Files.writeString(file, "Line 1\nLine 2\nLine 3");
            
            String content = IOExercises.readTextFile(file.toString());
            
            assertThat(content).contains("Line 1", "Line 2", "Line 3");
        }
        
        @Test
        @DisplayName("Записывает текстовый файл")
        void shouldWriteTextFile() throws IOException {
            Path file = tempDir.resolve("output.txt");
            
            IOExercises.writeTextFile(file.toString(), "Test content");
            
            assertThat(Files.readString(file)).isEqualTo("Test content");
        }
    }
    
    @Nested
    @DisplayName("countLines")
    class CountLines {
        
        @Test
        @DisplayName("Подсчитывает строки")
        void shouldCountLines() throws IOException {
            Path file = tempDir.resolve("lines.txt");
            Files.writeString(file, "Line 1\nLine 2\nLine 3\nLine 4");
            
            long count = IOExercises.countLines(file.toString());
            
            assertThat(count).isEqualTo(4);
        }
        
        @Test
        @DisplayName("Возвращает 0 для пустого файла")
        void shouldReturnZeroForEmptyFile() throws IOException {
            Path file = tempDir.resolve("empty.txt");
            Files.createFile(file);
            
            long count = IOExercises.countLines(file.toString());
            
            assertThat(count).isZero();
        }
    }
    
    @Nested
    @DisplayName("CountingInputStream")
    class CountingInputStreamTest {
        
        @Test
        @DisplayName("Подсчитывает прочитанные байты")
        void shouldCountBytes() throws IOException {
            byte[] data = "Hello, World!".getBytes();
            var counting = new IOExercises.CountingInputStream(
                new ByteArrayInputStream(data)
            );
            
            byte[] buffer = new byte[100];
            counting.read(buffer);
            
            assertThat(counting.getBytesRead()).isEqualTo(data.length);
        }
        
        @Test
        @DisplayName("Подсчитывает байты при чтении по одному")
        void shouldCountSingleBytes() throws IOException {
            byte[] data = "ABC".getBytes();
            var counting = new IOExercises.CountingInputStream(
                new ByteArrayInputStream(data)
            );
            
            counting.read();
            counting.read();
            
            assertThat(counting.getBytesRead()).isEqualTo(2);
        }
    }
}

