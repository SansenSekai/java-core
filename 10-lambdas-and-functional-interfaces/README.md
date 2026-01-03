# Модуль 10: Лямбды и функциональные интерфейсы

> **Уровень**: ⭐⭐⭐ Средний+  
> **Время**: 3-4 часа

## 📋 Теоретическое введение

### Функциональный интерфейс

Интерфейс с одним абстрактным методом (SAM):

```java
@FunctionalInterface
public interface MyFunction<T, R> {
    R apply(T t);
}
```

### Стандартные интерфейсы (java.util.function)

| Интерфейс | Метод | Сигнатура |
|-----------|-------|-----------|
| Function<T,R> | apply | T → R |
| Predicate<T> | test | T → boolean |
| Consumer<T> | accept | T → void |
| Supplier<T> | get | () → T |
| BiFunction<T,U,R> | apply | (T, U) → R |
| UnaryOperator<T> | apply | T → T |
| BinaryOperator<T> | apply | (T, T) → T |

### Method References

```java
String::length          // instance method
System.out::println     // instance method of specific object
Integer::parseInt       // static method
ArrayList::new          // constructor
```

### Effectively Final

Переменные в лямбдах должны быть effectively final:

```java
int x = 10;
// x = 20;  // ERROR: делает x не effectively final
Runnable r = () -> System.out.println(x);
```

## 🎯 Задание

### Задача 1: Кастомные функциональные интерфейсы

Создай:
- TriFunction<A, B, C, R>
- CheckedSupplier<T> (с throws Exception)

### Задача 2: Композиция функций

Реализуй compose() и andThen() для своего интерфейса.

### Задача 3: Currying

Преобразуй BiFunction в Function, возвращающий Function.

## ✅ Чек-лист

- [ ] Знаешь стандартные функциональные интерфейсы
- [ ] Понимаешь захват переменных (closure)
- [ ] Умеешь использовать method references
- [ ] Понимаешь effectively final

