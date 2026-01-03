# Модуль 18: Concurrent Collections

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-7 часов

## 📋 Теоретическое введение

### ConcurrentHashMap

- **Lock striping**: блокировка на уровне сегментов (до Java 8)
- **CAS + synchronized**: современная реализация (Java 8+)
- **Не блокирует чтение**
- Итератор weakly consistent (не бросает ConcurrentModificationException)

### CopyOnWriteArrayList

- Каждая модификация создаёт копию массива
- Чтение без блокировок
- **Хорошо для**: редкие записи, частые чтения
- **Плохо для**: частые записи

### BlockingQueue

```java
LinkedBlockingQueue      // unbounded (опционально bounded)
ArrayBlockingQueue       // bounded, FIFO
PriorityBlockingQueue    // unbounded, с приоритетами
SynchronousQueue         // без буфера, hand-off
```

## 🎯 Задание

### Задача 1: Producer-Consumer с BlockingQueue

Реализуй систему с:
- N producers
- M consumers
- Graceful shutdown

### Задача 2: Concurrent Cache

Реализуй LRU cache на базе ConcurrentHashMap.

### Задача 3: Сравнение производительности

Сравни Collections.synchronizedMap() vs ConcurrentHashMap.

## ✅ Чек-лист

- [ ] Понимаешь внутреннее устройство ConcurrentHashMap
- [ ] Знаешь weakly consistent iterators
- [ ] Понимаешь trade-offs CopyOnWriteArrayList
- [ ] Умеешь выбирать правильную BlockingQueue

