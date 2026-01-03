# Модуль 11: Stream API Mastery

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-7 часов

## 📋 Теоретическое введение

### Промежуточные операции (lazy)

```java
filter(predicate)    // фильтрация
map(function)        // преобразование
flatMap(function)    // T → Stream<R>
distinct()           // уникальные
sorted()             // сортировка
peek(consumer)       // отладка (побочный эффект)
limit(n), skip(n)    // ограничение
```

### Терминальные операции (eager)

```java
collect(collector)   // сбор в коллекцию
reduce(identity, op) // свёртка
forEach(consumer)    // побочный эффект
count(), min(), max()
anyMatch(), allMatch(), noneMatch()
findFirst(), findAny()
```

### Collectors

```java
Collectors.toList()
Collectors.toSet()
Collectors.toMap(keyMapper, valueMapper)
Collectors.groupingBy(classifier)
Collectors.partitioningBy(predicate)
Collectors.joining(delimiter)
```

## 🎯 Задание

### Задача 1: Сложные трансформации

Обработай список заказов:
- Группировка по клиенту
- Сумма по категориям
- Топ-N товаров

### Задача 2: Кастомный Collector

Реализуй collector для подсчёта статистики (min, max, avg, count).

### Задача 3: Parallel Streams

Сравни производительность sequential и parallel для разных задач.

## ✅ Чек-лист

- [ ] Понимаешь lazy evaluation
- [ ] Знаешь, когда parallel stream полезен/вреден
- [ ] Умеешь создавать кастомные collectors
- [ ] Избегаешь побочных эффектов в stream operations

