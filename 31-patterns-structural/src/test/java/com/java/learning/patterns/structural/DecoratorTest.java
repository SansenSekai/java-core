package com.java.learning.patterns.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Decorator Pattern")
class DecoratorTest {

    @Nested
    @DisplayName("Базовый DataSource")
    class BaseDataSource {
        
        @Test
        @DisplayName("StringDataSource хранит и возвращает данные")
        void shouldStoreAndRetrieveData() {
            var source = new Decorator.StringDataSource();
            
            source.writeData("Hello, World!");
            
            assertThat(source.readData()).isEqualTo("Hello, World!");
        }
    }
    
    @Nested
    @DisplayName("CompressionDecorator")
    class CompressionDecoratorTest {
        
        @Test
        @DisplayName("Сжимает и распаковывает данные")
        void shouldCompressAndDecompress() {
            var source = new Decorator.StringDataSource();
            var compressed = new Decorator.CompressionDecorator(source);
            
            String original = "Hello, World! ".repeat(100);
            compressed.writeData(original);
            
            // Данные в source должны быть сжаты (меньше оригинала)
            String stored = source.readData();
            assertThat(stored.length()).isLessThan(original.length());
            
            // Чтение через декоратор возвращает оригинал
            assertThat(compressed.readData()).isEqualTo(original);
        }
    }
    
    @Nested
    @DisplayName("EncryptionDecorator")
    class EncryptionDecoratorTest {
        
        @Test
        @DisplayName("Шифрует и расшифровывает данные")
        void shouldEncryptAndDecrypt() {
            var source = new Decorator.StringDataSource();
            var encrypted = new Decorator.EncryptionDecorator(source, (byte) 42);
            
            String original = "Secret message";
            encrypted.writeData(original);
            
            // Данные в source должны быть зашифрованы
            String stored = source.readData();
            assertThat(stored).isNotEqualTo(original);
            
            // Чтение через декоратор возвращает оригинал
            assertThat(encrypted.readData()).isEqualTo(original);
        }
    }
    
    @Nested
    @DisplayName("Цепочка декораторов")
    class DecoratorChain {
        
        @Test
        @DisplayName("Можно комбинировать декораторы")
        void shouldChainDecorators() {
            var source = new Decorator.StringDataSource();
            // Сначала сжимаем, потом шифруем
            var compressed = new Decorator.CompressionDecorator(source);
            var encrypted = new Decorator.EncryptionDecorator(compressed, (byte) 42);
            
            String original = "Confidential data ".repeat(100);
            encrypted.writeData(original);
            
            // Данные прошли через обе трансформации
            String stored = source.readData();
            assertThat(stored).isNotEqualTo(original);
            
            // Чтение через цепочку восстанавливает оригинал
            assertThat(encrypted.readData()).isEqualTo(original);
        }
    }
}

