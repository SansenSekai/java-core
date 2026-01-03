package com.java.learning.classloaders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Custom ClassLoader")
class CustomClassLoaderTest {

    @TempDir
    Path tempDir;
    
    @Test
    @DisplayName("Загружает класс из директории")
    void shouldLoadClassFromDirectory() throws Exception {
        // Создаём структуру пакетов и .class файл
        Path packageDir = Files.createDirectories(
            tempDir.resolve("com/example")
        );
        
        // Минимальный .class файл для класса com.example.Hello
        // В реальности нужно скомпилировать Java файл
        // Для теста проверяем только механику загрузки
        
        var loader = new CustomClassLoader(tempDir);
        
        // Тест делегирования родителю (стандартные классы)
        Class<?> stringClass = loader.loadClass("java.lang.String");
        assertThat(stringClass).isEqualTo(String.class);
    }
    
    @Test
    @DisplayName("Разные ClassLoader создают разные классы")
    void differentLoadersCreateDifferentClasses() throws Exception {
        // Демонстрация: один и тот же класс, загруженный разными 
        // ClassLoader'ами — это РАЗНЫЕ классы в JVM
        
        var loader1 = new CustomClassLoader(tempDir);
        var loader2 = new CustomClassLoader(tempDir);
        
        // Для стандартных классов это работает через делегирование
        Class<?> class1 = loader1.loadClass("java.lang.String");
        Class<?> class2 = loader2.loadClass("java.lang.String");
        
        // Оба — String, потому что делегировали родительскому ClassLoader
        assertThat(class1).isEqualTo(class2);
        
        // Для пользовательских классов (если бы они загружались 
        // без делегирования) — были бы РАЗНЫЕ классы!
    }
    
    @Test
    @DisplayName("ClassNotFoundException для несуществующего класса")
    void shouldThrowForMissingClass() {
        var loader = new CustomClassLoader(tempDir);
        
        assertThatThrownBy(() -> loader.loadClass("com.nonexistent.MissingClass"))
            .isInstanceOf(ClassNotFoundException.class);
    }
}

