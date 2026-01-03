# Модуль 25: NIO.2 Files API

> **Уровень**: ⭐⭐⭐ Средний+  
> **Время**: 4-5 часов

## 📋 Теоретическое введение

### Path

```java
Path path = Path.of("dir", "file.txt");
Path path = Paths.get("/home/user/file.txt");
path.getFileName()    // file.txt
path.getParent()      // dir
path.resolve("sub")   // dir/file.txt/sub
path.normalize()      // убрать . и ..
```

### Files

```java
Files.exists(path)
Files.isDirectory(path)
Files.createDirectories(path)
Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING)
Files.move(src, dst)
Files.delete(path)
Files.readString(path)
Files.writeString(path, content)
Files.lines(path)     // Stream<String>
```

### FileVisitor

```java
Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        // process file
        return FileVisitResult.CONTINUE;
    }
});
```

### WatchService

Отслеживание изменений в директории:

```java
WatchService watcher = FileSystems.getDefault().newWatchService();
path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
```

## 🎯 Задание

### Задача 1: Directory Size

Рекурсивно подсчитай размер директории.

### Задача 2: Find Duplicates

Найди файлы-дубликаты по содержимому (hash).

### Задача 3: File Watcher

Отслеживай изменения и логируй их.

## ✅ Чек-лист

- [ ] Используешь Path вместо File
- [ ] Знаешь про Files.walk() и Files.find()
- [ ] Умеешь работать с атрибутами файлов
- [ ] Понимаешь WatchService

