# Модуль 12: Optional и Null Safety

> **Уровень**: ⭐⭐ Средний  
> **Время**: 2-3 часа

## 📋 Теоретическое введение

### Создание Optional

```java
Optional.of(value)          // value != null, иначе NPE
Optional.ofNullable(value)  // value может быть null
Optional.empty()            // пустой Optional
```

### Извлечение значения

```java
opt.get()                        // опасно! бросает исключение
opt.orElse(default)              // значение или default
opt.orElseGet(() -> compute())   // ленивое вычисление default
opt.orElseThrow()                // бросить NoSuchElementException
opt.orElseThrow(CustomException::new)
```

### Трансформация

```java
opt.map(fn)        // Optional<T> → Optional<R>
opt.flatMap(fn)    // Optional<T> → Optional<R>, fn returns Optional
opt.filter(pred)   // Optional<T> → Optional<T> или empty
```

### Anti-patterns

❌ `if (opt.isPresent()) { opt.get(); }`  
✅ `opt.ifPresent(value -> ...)`

❌ `return opt.isPresent() ? opt.get() : null;`  
✅ `return opt.orElse(null);`

❌ `Optional<List<T>>`  
✅ Вернуть пустой список

## 🎯 Задание

### Задача 1: Цепочка Optional

Навигация по вложенным объектам без NPE.

### Задача 2: Рефакторинг null-проверок

Перепиши код с if-null на Optional.

### Задача 3: Optional в API

Когда использовать Optional в return type, а когда нет?

## ✅ Чек-лист

- [ ] Не используешь get() без проверки
- [ ] Используешь orElse/orElseGet правильно
- [ ] Знаешь, когда Optional избыточен
- [ ] Не используешь Optional для полей

