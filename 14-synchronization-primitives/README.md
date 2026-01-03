# Модуль 14: Примитивы синхронизации

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-7 часов

## 📋 Теоретическое введение

### synchronized

```java
synchronized (lock) {
    // critical section
}

synchronized void method() {
    // this is the lock
}

static synchronized void staticMethod() {
    // Class object is the lock
}
```

### volatile

Гарантирует:
1. **Visibility**: изменения видны другим потокам
2. **No reordering**: операции не переупорядочиваются

НЕ гарантирует:
- Atomicity (кроме read/write)

### wait/notify

```java
synchronized (lock) {
    while (!condition) {
        lock.wait();  // releases lock, waits for notify
    }
    // do work
    lock.notifyAll();  // wake up waiting threads
}
```

## 🎯 Задание

### Задача 1: Thread-safe Counter

Реализуй счётчик тремя способами:
1. synchronized
2. volatile + CAS
3. AtomicInteger

### Задача 2: Bounded Buffer

Реализуй ограниченный буфер с wait/notify:
- put() блокируется, если буфер полон
- take() блокируется, если буфер пуст

### Задача 3: Read-Write Lock

Реализуй свой RW lock с помощью synchronized и wait/notify.

## ✅ Чек-лист

- [ ] Понимаешь, что такое monitor
- [ ] Знаешь, когда использовать volatile vs synchronized
- [ ] Понимаешь spurious wakeup
- [ ] Знаешь про happens-before для volatile

