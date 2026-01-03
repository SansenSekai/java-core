package com.java.learning.patterns.structural;

import java.io.*;

/**
 * Паттерн Decorator — динамическое добавление функциональности.
 * 
 * Классический пример в JDK: java.io streams.
 * new BufferedReader(new InputStreamReader(new FileInputStream(file)))
 * 
 * Ключевые признаки:
 * - Декоратор реализует тот же интерфейс, что и декорируемый объект
 * - Декоратор делегирует работу обёрнутому объекту
 * - Добавляет поведение до/после делегирования
 * 
 * TODO: Реализуй декораторы для DataSource
 */
public class Decorator {
    
    /**
     * Базовый интерфейс источника данных.
     */
    public interface DataSource {
        void writeData(String data);
        String readData();
    }
    
    /**
     * Простая реализация: данные в памяти.
     */
    public static class StringDataSource implements DataSource {
        private String data = "";
        
        @Override
        public void writeData(String data) {
            this.data = data;
        }
        
        @Override
        public String readData() {
            return data;
        }
    }
    
    /**
     * Базовый декоратор.
     */
    public static abstract class DataSourceDecorator implements DataSource {
        protected final DataSource wrapped;
        
        protected DataSourceDecorator(DataSource wrapped) {
            this.wrapped = wrapped;
        }
        
        @Override
        public void writeData(String data) {
            wrapped.writeData(data);
        }
        
        @Override
        public String readData() {
            return wrapped.readData();
        }
    }
    
    /**
     * Декоратор: сжатие данных.
     * 
     * TODO: Реализуй сжатие/распаковку
     */
    public static class CompressionDecorator extends DataSourceDecorator {
        
        public CompressionDecorator(DataSource source) {
            super(source);
        }
        
        @Override
        public void writeData(String data) {
            // TODO: Сожми данные перед записью
            // Подсказка: Base64.encode(GZIPOutputStream)
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        @Override
        public String readData() {
            // TODO: Распакуй данные после чтения
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Декоратор: шифрование данных.
     * 
     * TODO: Реализуй простое шифрование (XOR)
     */
    public static class EncryptionDecorator extends DataSourceDecorator {
        private final byte key;
        
        public EncryptionDecorator(DataSource source, byte key) {
            super(source);
            this.key = key;
        }
        
        @Override
        public void writeData(String data) {
            // TODO: Зашифруй данные
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        @Override
        public String readData() {
            // TODO: Расшифруй данные
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
    
    /**
     * Декоратор: логирование операций.
     */
    public static class LoggingDecorator extends DataSourceDecorator {
        
        public LoggingDecorator(DataSource source) {
            super(source);
        }
        
        @Override
        public void writeData(String data) {
            // TODO: Логируй размер данных и время
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        @Override
        public String readData() {
            // TODO: Логируй время чтения
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

