# Модуль 24: I/O Streams

> **Уровень**: ⭐⭐ Средний  
> **Время**: 3-4 часа

## 📋 Теоретическое введение

### Иерархия потоков

**Байтовые потоки**:
- InputStream / OutputStream — абстрактные классы
- FileInputStream / FileOutputStream — файлы
- ByteArrayInputStream / ByteArrayOutputStream — массивы
- BufferedInputStream / BufferedOutputStream — буферизация
- DataInputStream / DataOutputStream — примитивы
- ObjectInputStream / ObjectOutputStream — сериализация

**Символьные потоки**:
- Reader / Writer — абстрактные классы
- FileReader / FileWriter — файлы
- BufferedReader / BufferedWriter — буферизация
- InputStreamReader / OutputStreamWriter — мост byte↔char
- PrintWriter — форматированный вывод

### Паттерн Decorator

```java
InputStream in = new BufferedInputStream(
    new FileInputStream("file.txt")
);
```

### Try-with-resources

```java
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        // process
    }
}
```

## 🎯 Задание

### Задача 1: Копирование файла

Реализуй эффективное копирование с буферизацией.

### Задача 2: Подсчёт слов

Подсчитай слова в большом текстовом файле.

### Задача 3: Кастомный InputStream

Реализуй декоратор, который считает прочитанные байты.

## ✅ Чек-лист

- [ ] Понимаешь разницу byte vs char streams
- [ ] Умеешь правильно закрывать ресурсы
- [ ] Знаешь про буферизацию
- [ ] Понимаешь паттерн Decorator в I/O

