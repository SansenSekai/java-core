package com.java.learning.modules;

/**
 * Пример работы с Java Module System (JPMS).
 * 
 * JPMS (Java 9+) — система модулей для:
 * - Строгая инкапсуляция (даже public классы могут быть скрыты)
 * - Явные зависимости между модулями
 * - Меньший размер runtime (можно включить только нужные модули)
 * 
 * Ключевой файл: module-info.java
 * 
 * ```java
 * module com.myapp {
 *     requires java.sql;           // Зависимость
 *     requires transitive java.logging; // Транзитивная
 *     
 *     exports com.myapp.api;       // Публичный API
 *     exports com.myapp.internal to com.myapp.impl; // Ограниченный
 *     
 *     opens com.myapp.model;       // Для reflection
 *     
 *     uses com.myapp.spi.Plugin;   // Использует сервис
 *     provides com.myapp.spi.Plugin with com.myapp.impl.MyPlugin;
 * }
 * ```
 * 
 * TODO: Создай модульное приложение с несколькими модулями
 */
public class ModuleExample {
    
    /**
     * Демонстрация работы с модулями.
     */
    public static void demonstrateModules() {
        // Получение модуля текущего класса
        Module module = ModuleExample.class.getModule();
        
        System.out.println("Module name: " + module.getName());
        System.out.println("Is named: " + module.isNamed());
        
        // Проверка доступа
        // module.isExported("com.java.learning.modules");
        // module.isOpen("com.java.learning.modules");
        
        // Зависимости
        // module.getDescriptor().requires();
    }
    
    /**
     * Пример ServiceLoader для модульных сервисов.
     */
    public static void serviceLoaderExample() {
        // ServiceLoader работает с JPMS:
        // 
        // В module-info.java:
        //   uses com.myapp.spi.Plugin;
        // 
        // В реализующем модуле:
        //   provides com.myapp.spi.Plugin with com.myapp.impl.MyPluginImpl;
        // 
        // Код:
        // ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);
        // for (Plugin plugin : loader) {
        //     plugin.execute();
        // }
    }
    
    /**
     * Миграция на модули.
     * 
     * Стратегии:
     * 1. Bottom-up: начни с зависимостей без зависимостей
     * 2. Top-down: начни с приложения, используй automatic modules
     * 3. Automatic modules: JAR на module-path без module-info
     */
    public static void migrationStrategies() {
        // Automatic module:
        // - JAR без module-info.java на module-path
        // - Имя модуля = имя JAR (очищенное)
        // - Экспортирует все пакеты
        // - Читает все модули
        
        // Split packages:
        // - Один пакет не может быть в нескольких модулях
        // - Нужно рефакторить или объединять
    }
}

