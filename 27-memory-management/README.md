# Модуль 27: Управление памятью

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-7 часов

## 📋 Теоретическое введение

### Области памяти JVM

```
┌─────────────────────────────────────────────────────────────┐
│                          JVM Memory                         │
├─────────────────────────────────────────────────────────────┤
│  Heap (объекты)              │  Non-Heap                    │
│  ├── Young Generation        │  ├── Metaspace (классы)      │
│  │   ├── Eden                │  ├── Code Cache (JIT)        │
│  │   ├── Survivor 0          │  ├── Direct Memory (NIO)     │
│  │   └── Survivor 1          │  └── Thread Stacks           │
│  └── Old Generation          │                              │
└─────────────────────────────────────────────────────────────┘
```

### String Pool (Критично для собеседований!)

String Pool — это специальная область памяти для хранения строковых литералов.

```java
String s1 = "hello";              // В String Pool
String s2 = "hello";              // Тот же объект из Pool
String s3 = new String("hello");  // Новый объект в Heap (НЕ в Pool!)
String s4 = s3.intern();          // Добавляет в Pool, возвращает из Pool

s1 == s2  // true  — один объект
s1 == s3  // false — разные объекты
s1 == s4  // true  — intern() вернул объект из Pool
```

**Где расположен String Pool?**
- Java 6 и раньше: PermGen (фиксированный размер!)
- Java 7+: Heap (растёт с heap, GC работает)

**Типичные ошибки:**
```java
// ПЛОХО: каждый раз создаётся новый объект
for (int i = 0; i < 1000000; i++) {
    String s = new String("const"); // НЕ делай так!
}

// ХОРОШО: один объект из Pool
String constant = "const";
for (int i = 0; i < 1000000; i++) {
    use(constant);
}
```

**Когда использовать intern()?**
- Много одинаковых строк из внешних источников (XML, JSON)
- Строки используются как ключи Map
- ⚠️ НЕ используй для уникальных строк — только тратит память!

### Garbage Collectors (Java 17+)

| GC | Особенность | Паузы | Когда использовать |
|----|------------|-------|-------------------|
| Serial | Однопоточный | Длинные | Клиенты, маленькие приложения |
| Parallel | Многопоточный, throughput | Средние | Batch processing |
| G1 | Регионы, предсказуемые паузы | <200ms | Общего назначения (default) |
| ZGC | Concurrent, scalable | <1ms | Большие heap (ТБ), low latency |
| Shenandoah | Concurrent compacting | <10ms | Low latency, Red Hat |

### GC Generational Hypothesis

Большинство объектов умирают молодыми!

```
┌─────────────────────────────────────────────────────────────┐
│ Eden  → [Minor GC] → S0 ↔ S1 → [Promotion] → Old Gen        │
│                         (survive N times)                    │
└─────────────────────────────────────────────────────────────┘
```

- **Minor GC**: быстрый, только Young Gen
- **Major GC**: медленнее, Old Gen
- **Full GC**: весь heap, самый медленный

### GC Tuning Basics

```bash
# Размеры heap
-Xms4g -Xmx4g           # Min и Max (лучше одинаковые)
-XX:NewRatio=2          # Old:Young = 2:1
-XX:SurvivorRatio=8     # Eden:Survivor = 8:1

# Выбор GC
-XX:+UseG1GC            # G1 (default в Java 9+)
-XX:+UseZGC             # ZGC (Java 15+)
-XX:+UseShenandoahGC    # Shenandoah

# G1 tuning
-XX:MaxGCPauseMillis=200    # Target pause time
-XX:G1HeapRegionSize=16m    # Region size (1-32MB)

# Логирование GC (Java 9+)
-Xlog:gc*:file=gc.log:time,uptime:filecount=5,filesize=10m
```

### Object Layout и Memory Overhead

```java
// Object header: 12 bytes (64-bit JVM с compressed oops)
// + поля выровнены
// + padding до кратности 8

class EmptyObject { }           // 16 bytes (12 header + 4 padding)
class OneInt { int x; }         // 16 bytes (12 header + 4 int)
class TwoInts { int x, y; }     // 24 bytes (12 + 4 + 4 + 4 padding)
class OneRef { Object o; }      // 16 bytes (12 + 4 ref with compressed)
```

**Почему ArrayList<Integer> "дорогой":**
```java
int[] arr = new int[10];
// 16 bytes header + 10 * 4 bytes = 56 bytes

Integer[] arr = new Integer[10];
// 16 bytes header + 10 * 4 refs = 56 bytes
// + каждый Integer: 16 bytes
// Итого: 56 + 10 * 16 = 216 bytes!  (4x разница!)
```

## 🎯 Задание

### Задача 1: MemoryInfo утилита

Реализуй класс с методами:
- `getHeapInfo()` — used, max, percent
- `getNonHeapInfo()` — metaspace usage
- `getGCCount()` — количество GC событий
- `forceGC()` — вызов System.gc() с ожиданием

### Задача 2: String Pool Experiments

1. Продемонстрируй разницу между `"hello"` и `new String("hello")`
2. Покажи эффект `intern()`
3. Измерь потребление памяти с intern() и без

### Задача 3: GC Analysis

1. Создай программу с высоким allocation rate
2. Запусти с разными GC и сравни throughput/latency
3. Проанализируй GC логи

### Задача 4: Heap Dump Analysis

1. Создай heap dump (`jmap -dump:format=b,file=heap.hprof <pid>`)
2. Открой в Eclipse MAT или VisualVM
3. Найди dominators, подозрительные коллекции

## ✅ Чек-лист

- [ ] Знаешь структуру памяти JVM (Heap, Metaspace, Stack)
- [ ] Понимаешь String Pool и когда использовать intern()
- [ ] Знаешь object layout и memory overhead
- [ ] Понимаешь generational hypothesis
- [ ] Знаешь разницу между GC алгоритмами
- [ ] Умеешь читать GC логи
- [ ] Умеешь анализировать heap dumps
- [ ] Знаешь основные флаги JVM для memory tuning

## 🔬 Мета-вопросы для размышления

1. Почему `String s = "a" + "b"` создаёт один объект, а `String s = a + b` — может создать три?
2. Когда стоит использовать `intern()`, а когда это вредно?
3. Почему ArrayList<Integer> занимает намного больше памяти, чем int[]?
4. Как выбрать между G1 и ZGC?
5. Что такое "GC overhead limit exceeded"?
