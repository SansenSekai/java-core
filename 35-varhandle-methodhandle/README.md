# Модуль 35: VarHandle и MethodHandle

> **Уровень**: ⭐⭐⭐⭐⭐ Эксперт  
> **Время**: 7-9 часов

## 📋 Теоретическое введение

### MethodHandle

Типобезопасная альтернатива reflection:

```java
MethodHandles.Lookup lookup = MethodHandles.lookup();
MethodHandle mh = lookup.findVirtual(
    String.class,
    "length",
    MethodType.methodType(int.class)
);
int len = (int) mh.invoke("hello"); // 5
```

**Преимущества над reflection**:
- Быстрее (JIT может инлайнить)
- Типобезопасность на уровне MethodType
- Можно комбинировать (insertArguments, filterReturnValue и т.д.)

### VarHandle (Java 9+)

Типобезопасный доступ к полям с поддержкой атомарных операций:

```java
class Point {
    volatile int x;
    static final VarHandle X;
    static {
        try {
            X = MethodHandles.lookup()
                .findVarHandle(Point.class, "x", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

// Использование
Point p = new Point();
X.set(p, 10);                    // обычная запись
X.setVolatile(p, 20);            // volatile запись
X.compareAndSet(p, 20, 30);      // CAS
X.getAndAdd(p, 5);               // атомарное добавление
```

### Режимы доступа VarHandle

- **Plain**: обычный доступ (как обычное поле)
- **Opaque**: гарантия порядка для одной переменной
- **Acquire/Release**: acquire-release семантика
- **Volatile**: полная volatile семантика

## 🎯 Задание

### Задача 1: Dynamic Dispatch

Реализуй вызов метода по имени через MethodHandle.

### Задача 2: Lock-free Counter

Атомарный счётчик через VarHandle без AtomicInteger.

### Задача 3: Field Access Optimizer

Сравни производительность: reflection vs MethodHandle vs VarHandle.

## ✅ Чек-лист

- [ ] Понимаешь MethodType и сигнатуры
- [ ] Умеешь создавать MethodHandle для разных случаев
- [ ] Знаешь режимы доступа VarHandle
- [ ] Понимаешь, когда использовать вместо reflection

