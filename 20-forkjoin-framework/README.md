# Модуль 20: Fork/Join Framework

> **Уровень**: ⭐⭐⭐⭐ Сложный  
> **Время**: 5-6 часов

## 📋 Теоретическое введение

### ForkJoinPool

Пул потоков, оптимизированный для divide-and-conquer задач.

```java
ForkJoinPool pool = ForkJoinPool.commonPool();
Integer result = pool.invoke(new MyRecursiveTask());
```

### RecursiveTask<V>

Возвращает результат:

```java
class SumTask extends RecursiveTask<Long> {
    @Override
    protected Long compute() {
        if (size < THRESHOLD) {
            return computeDirectly();
        }
        SumTask left = new SumTask(leftHalf);
        SumTask right = new SumTask(rightHalf);
        left.fork();  // асинхронно запустить
        Long rightResult = right.compute();  // вычислить в текущем потоке
        Long leftResult = left.join();  // дождаться результата
        return leftResult + rightResult;
    }
}
```

### RecursiveAction

Без результата (void):

```java
class SortAction extends RecursiveAction {
    @Override
    protected void compute() {
        if (size < THRESHOLD) {
            Arrays.sort(array, start, end);
            return;
        }
        invokeAll(new SortAction(left), new SortAction(right));
    }
}
```

### Work Stealing

- Каждый поток имеет свою deque задач
- При простое "ворует" задачи у других потоков
- Эффективно для несбалансированных задач

## 🎯 Задание

### Задача 1: Параллельная сумма массива

RecursiveTask для суммирования большого массива.

### Задача 2: Параллельный Merge Sort

RecursiveAction для сортировки слиянием.

### Задача 3: Параллельный поиск в дереве

Поиск элемента в большом дереве.

## ✅ Чек-лист

- [ ] Понимаешь work stealing
- [ ] Знаешь разницу RecursiveTask vs RecursiveAction
- [ ] Умеешь выбирать правильный threshold
- [ ] Понимаешь, когда ForkJoin лучше ExecutorService

