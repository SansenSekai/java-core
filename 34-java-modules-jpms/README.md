# Модуль 34: Java Modules (JPMS)

> **Уровень**: ⭐⭐⭐ Средний+  
> **Время**: 4-5 часов

## 📋 Теоретическое введение

### module-info.java

```java
module com.myapp {
    requires java.sql;           // зависимость
    requires transitive java.logging; // транзитивная
    
    exports com.myapp.api;       // публичный API
    exports com.myapp.internal to com.myapp.impl; // ограниченный
    
    opens com.myapp.model;       // для reflection (runtime)
    opens com.myapp.entity to hibernate.core;
    
    uses com.myapp.spi.Plugin;   // использует сервис
    provides com.myapp.spi.Plugin with com.myapp.impl.MyPlugin;
}
```

### Ключевые директивы

| Директива | Описание |
|-----------|----------|
| `requires` | Зависимость от другого модуля |
| `requires transitive` | Зависимость передаётся зависимым модулям |
| `exports` | Делает пакет доступным |
| `exports ... to` | Ограниченный экспорт |
| `opens` | Открывает для reflection в runtime |
| `uses` | Объявляет использование сервиса |
| `provides ... with` | Предоставляет реализацию сервиса |

### ServiceLoader

```java
ServiceLoader<Plugin> loader = ServiceLoader.load(Plugin.class);
for (Plugin plugin : loader) {
    plugin.execute();
}
```

## 🎯 Задание

### Задача 1: Модульное приложение

Создай многомодульное приложение: api, impl, app.

### Задача 2: Service Provider

Реализуй плагинную систему через ServiceLoader.

### Задача 3: Миграция

Мигрируй classpath приложение на module-path.

## ✅ Чек-лист

- [ ] Понимаешь разницу exports vs opens
- [ ] Знаешь про split packages
- [ ] Умеешь использовать ServiceLoader
- [ ] Понимаешь automatic modules

