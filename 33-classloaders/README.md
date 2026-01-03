# Модуль 33: ClassLoaders

> **Уровень**: ⭐⭐⭐⭐⭐ Эксперт  
> **Время**: 6-8 часов

## 📋 Теоретическое введение

### Иерархия ClassLoader

```
Bootstrap ClassLoader (native)
    ↓
Platform ClassLoader (Java 9+) / Extension ClassLoader (до Java 9)
    ↓
Application ClassLoader
    ↓
Custom ClassLoaders
```

### Делегирование

По умолчанию: **parent-first** — сначала спрашиваем родителя.

Можно реализовать **child-first** для изоляции.

### Загрузка класса

1. **Loading**: найти .class файл, прочитать bytecode
2. **Linking**:
   - Verification: проверка bytecode
   - Preparation: выделение памяти для static полей
   - Resolution: разрешение символических ссылок
3. **Initialization**: выполнение static блоков

### Кастомный ClassLoader

```java
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = loadClassBytes(name);
        return defineClass(name, bytes, 0, bytes.length);
    }
}
```

## 🎯 Задание

### Задача 1: Hot Reload

Загрузка новой версии класса без перезапуска JVM.

### Задача 2: Plugin System

Изоляция плагинов через отдельные ClassLoaders.

### Задача 3: Class Encryption

Загрузка зашифрованных .class файлов.

## ✅ Чек-лист

- [ ] Понимаешь делегирование
- [ ] Знаешь про класс-идентичность (Class + ClassLoader)
- [ ] Умеешь создавать кастомный ClassLoader
- [ ] Понимаешь проблемы memory leaks с ClassLoaders

