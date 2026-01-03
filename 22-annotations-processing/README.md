# Модуль 22: Обработка аннотаций

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-6 часов

## 📋 Теоретическое введение

### Создание аннотации

```java
@Retention(RetentionPolicy.RUNTIME)  // доступна в runtime
@Target(ElementType.METHOD)           // применяется к методам
public @interface MyAnnotation {
    String value() default "";
    int priority() default 0;
}
```

### RetentionPolicy

- **SOURCE**: только в исходниках (Lombok)
- **CLASS**: в .class файлах, но не в runtime
- **RUNTIME**: доступна через reflection

### Target

- TYPE, FIELD, METHOD, PARAMETER
- CONSTRUCTOR, LOCAL_VARIABLE
- ANNOTATION_TYPE, PACKAGE
- TYPE_USE (Java 8+)

## 🎯 Задание

### Задача 1: @Validate

Создай аннотацию для валидации полей:
- @NotNull, @Size(min, max), @Pattern(regex)
- Валидатор, использующий reflection

### Задача 2: @Retry

Аннотация для повторных попыток:
- maxAttempts, delay, exceptions
- Обработчик через Proxy

### Задача 3: @Benchmark

Измерение времени выполнения методов.

## ✅ Чек-лист

- [ ] Понимаешь Retention и Target
- [ ] Умеешь читать аннотации через reflection
- [ ] Знаешь про repeatable annotations
- [ ] Понимаешь inherited annotations

