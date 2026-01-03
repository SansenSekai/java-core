# Модуль 19: Locks и Conditions

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-6 часов

## 📋 Теоретическое введение

### ReentrantLock

Более гибкая альтернатива synchronized:

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

**Преимущества**:
- tryLock() — неблокирующая попытка захвата
- tryLock(timeout) — с таймаутом
- lockInterruptibly() — можно прервать
- Fairness — честная очередь

### ReadWriteLock

Разделяет читателей и писателей:

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();
Lock readLock = rwLock.readLock();   // много читателей одновременно
Lock writeLock = rwLock.writeLock(); // только один писатель
```

### StampedLock (Java 8+)

Оптимистичное чтение без блокировки:

```java
long stamp = lock.tryOptimisticRead();
// read data
if (!lock.validate(stamp)) {
    stamp = lock.readLock();
    // re-read data
}
```

### Condition

Более гибкая альтернатива wait/notify:

```java
Condition notEmpty = lock.newCondition();
notEmpty.await();    // как wait()
notEmpty.signal();   // как notify()
notEmpty.signalAll();
```

## 🎯 Задание

### Задача 1: Bounded Buffer с Lock

Реализуй ограниченный буфер с ReentrantLock и двумя Condition.

### Задача 2: Read-Write Cache

Кэш с ReadWriteLock для оптимизации чтения.

### Задача 3: StampedLock Counter

Счётчик с оптимистичным чтением.

## ✅ Чек-лист

- [ ] Понимаешь разницу Lock vs synchronized
- [ ] Знаешь про fairness и его влияние на производительность
- [ ] Умеешь использовать Condition
- [ ] Понимаешь StampedLock и его режимы

