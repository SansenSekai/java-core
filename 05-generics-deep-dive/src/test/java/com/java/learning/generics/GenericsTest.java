package com.java.learning.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Generics Deep Dive")
class GenericsTest {
    
    @Nested
    @DisplayName("Pair")
    class PairTests {
        
        @Test
        @DisplayName("Создание и получение элементов")
        void shouldCreateAndGetElements() {
            Pair<String, Integer> pair = Pair.of("hello", 42);
            
            assertThat(pair.getFirst()).isEqualTo("hello");
            assertThat(pair.getSecond()).isEqualTo(42);
        }
        
        @Test
        @DisplayName("Пара с null элементами")
        void shouldAllowNullElements() {
            Pair<String, String> pair = Pair.of(null, null);
            
            assertThat(pair.getFirst()).isNull();
            assertThat(pair.getSecond()).isNull();
        }
        
        @Test
        @DisplayName("withFirst создаёт новую пару")
        void withFirstShouldCreateNewPair() {
            Pair<String, Integer> original = Pair.of("hello", 42);
            Pair<String, Integer> modified = original.withFirst("world");
            
            assertThat(original.getFirst()).isEqualTo("hello");
            assertThat(modified.getFirst()).isEqualTo("world");
            assertThat(modified.getSecond()).isEqualTo(42);
        }
        
        @Test
        @DisplayName("swap меняет элементы местами")
        void swapShouldExchangeElements() {
            Pair<String, Integer> pair = Pair.of("hello", 42);
            Pair<Integer, String> swapped = pair.swap();
            
            assertThat(swapped.getFirst()).isEqualTo(42);
            assertThat(swapped.getSecond()).isEqualTo("hello");
        }
        
        @Test
        @DisplayName("equals и hashCode")
        void shouldImplementEqualsAndHashCode() {
            Pair<String, Integer> pair1 = Pair.of("hello", 42);
            Pair<String, Integer> pair2 = Pair.of("hello", 42);
            Pair<String, Integer> pair3 = Pair.of("world", 42);
            
            assertThat(pair1).isEqualTo(pair2);
            assertThat(pair1.hashCode()).isEqualTo(pair2.hashCode());
            assertThat(pair1).isNotEqualTo(pair3);
        }
    }
    
    @Nested
    @DisplayName("ComparablePair")
    class ComparablePairTests {
        
        @Test
        @DisplayName("Сравнение по первому элементу")
        void shouldCompareByFirstElement() {
            ComparablePair<String, Integer> pair1 = ComparablePair.of("a", 1);
            ComparablePair<String, Integer> pair2 = ComparablePair.of("b", 1);
            
            assertThat(pair1.compareTo(pair2)).isLessThan(0);
        }
        
        @Test
        @DisplayName("Сравнение по второму элементу при равных первых")
        void shouldCompareBySecondWhenFirstEqual() {
            ComparablePair<String, Integer> pair1 = ComparablePair.of("a", 1);
            ComparablePair<String, Integer> pair2 = ComparablePair.of("a", 2);
            
            assertThat(pair1.compareTo(pair2)).isLessThan(0);
        }
        
        @Test
        @DisplayName("Сортировка списка пар")
        void shouldSortListOfPairs() {
            List<ComparablePair<String, Integer>> pairs = new ArrayList<>(List.of(
                ComparablePair.of("b", 2),
                ComparablePair.of("a", 1),
                ComparablePair.of("a", 2)
            ));
            
            Collections.sort(pairs);
            
            assertThat(pairs).containsExactly(
                ComparablePair.of("a", 1),
                ComparablePair.of("a", 2),
                ComparablePair.of("b", 2)
            );
        }
    }
    
    @Nested
    @DisplayName("Triple")
    class TripleTests {
        
        @Test
        @DisplayName("Создание и получение элементов")
        void shouldCreateAndGetElements() {
            Triple<String, Integer, Double> triple = Triple.of("hello", 42, 3.14);
            
            assertThat(triple.getFirst()).isEqualTo("hello");
            assertThat(triple.getSecond()).isEqualTo(42);
            assertThat(triple.getThird()).isEqualTo(3.14);
        }
        
        @Test
        @DisplayName("toFirstPair возвращает первые два элемента")
        void toFirstPairShouldReturnFirstTwo() {
            Triple<String, Integer, Double> triple = Triple.of("a", 1, 2.0);
            Pair<String, Integer> pair = triple.toFirstPair();
            
            assertThat(pair.getFirst()).isEqualTo("a");
            assertThat(pair.getSecond()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("toLastPair возвращает последние два элемента")
        void toLastPairShouldReturnLastTwo() {
            Triple<String, Integer, Double> triple = Triple.of("a", 1, 2.0);
            Pair<Integer, Double> pair = triple.toLastPair();
            
            assertThat(pair.getFirst()).isEqualTo(1);
            assertThat(pair.getSecond()).isEqualTo(2.0);
        }
    }
    
    @Nested
    @DisplayName("GenericUtils")
    class GenericUtilsTests {
        
        @Test
        @DisplayName("max находит максимальный элемент")
        void shouldFindMax() {
            List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6);
            
            Integer max = GenericUtils.max(numbers);
            
            assertThat(max).isEqualTo(9);
        }
        
        @Test
        @DisplayName("min находит минимальный элемент")
        void shouldFindMin() {
            List<String> strings = List.of("banana", "apple", "cherry");
            
            String min = GenericUtils.min(strings);
            
            assertThat(min).isEqualTo("apple");
        }
        
        @Test
        @DisplayName("max бросает исключение для пустой коллекции")
        void maxShouldThrowForEmptyCollection() {
            List<String> emptyList = List.of();
            assertThatThrownBy(() -> GenericUtils.max(emptyList))
                .isInstanceOf(NoSuchElementException.class);
        }
        
        @Test
        @DisplayName("copy копирует элементы (PECS)")
        void shouldCopyElements() {
            List<Integer> source = List.of(1, 2, 3);
            List<Number> dest = new ArrayList<>();
            
            GenericUtils.copy(source, dest);
            
            assertThat(dest).containsExactly(1, 2, 3);
        }
        
        @Test
        @DisplayName("filter фильтрует элементы")
        void shouldFilterElements() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5);
            
            List<Integer> even = GenericUtils.filter(numbers, n -> n % 2 == 0);
            
            assertThat(even).containsExactly(2, 4);
        }
        
        @Test
        @DisplayName("map преобразует элементы")
        void shouldMapElements() {
            List<String> strings = List.of("a", "bb", "ccc");
            
            List<Integer> lengths = GenericUtils.map(strings, String::length);
            
            assertThat(lengths).containsExactly(1, 2, 3);
        }
        
        @Test
        @DisplayName("invert инвертирует Map")
        void shouldInvertMap() {
            Map<String, Integer> original = Map.of("a", 1, "b", 2);
            
            Map<Integer, String> inverted = GenericUtils.invert(original);
            
            assertThat(inverted).containsEntry(1, "a").containsEntry(2, "b");
        }
        
        @Test
        @DisplayName("invert бросает исключение при дублирующихся значениях")
        void invertShouldThrowForDuplicateValues() {
            Map<String, Integer> map = Map.of("a", 1, "b", 1);
            
            assertThatThrownBy(() -> GenericUtils.invert(map))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("groupBy группирует элементы")
        void shouldGroupElements() {
            List<String> strings = List.of("a", "bb", "c", "dd", "eee");
            
            Map<Integer, List<String>> grouped = GenericUtils.groupBy(strings, String::length);
            
            assertThat(grouped.get(1)).containsExactlyInAnyOrder("a", "c");
            assertThat(grouped.get(2)).containsExactlyInAnyOrder("bb", "dd");
            assertThat(grouped.get(3)).containsExactly("eee");
        }
        
        @Test
        @DisplayName("zip объединяет два списка")
        void shouldZipLists() {
            List<String> first = List.of("a", "b", "c");
            List<Integer> second = List.of(1, 2, 3);
            
            List<Pair<String, Integer>> zipped = GenericUtils.zip(first, second);
            
            assertThat(zipped).containsExactly(
                Pair.of("a", 1),
                Pair.of("b", 2),
                Pair.of("c", 3)
            );
        }
        
        @Test
        @DisplayName("zip использует минимальную длину")
        void zipShouldUseMinLength() {
            List<String> first = List.of("a", "b");
            List<Integer> second = List.of(1, 2, 3, 4);
            
            List<Pair<String, Integer>> zipped = GenericUtils.zip(first, second);
            
            assertThat(zipped).hasSize(2);
        }
    }
    
    @Nested
    @DisplayName("InMemoryRepository")
    class InMemoryRepositoryTests {
        
        static class TestEntity implements Identifiable<Long> {
            private Long id;
            private String name;
            
            public TestEntity(String name) {
                this.name = name;
            }
            
            @Override
            public Long getId() { return id; }
            
            @Override
            public void setId(Long id) { this.id = id; }
            
            public String getName() { return name; }
        }
        
        private InMemoryRepository<TestEntity, Long> createRepository() {
            AtomicLong idGenerator = new AtomicLong(1);
            return new InMemoryRepository<>(e -> idGenerator.getAndIncrement());
        }
        
        @Test
        @DisplayName("save сохраняет сущность и назначает ID")
        void shouldSaveEntityAndAssignId() {
            var repo = createRepository();
            TestEntity entity = new TestEntity("Test");
            
            TestEntity saved = repo.save(entity);
            
            assertThat(saved.getId()).isNotNull();
            assertThat(repo.count()).isEqualTo(1);
        }
        
        @Test
        @DisplayName("findById находит сохранённую сущность")
        void shouldFindById() {
            var repo = createRepository();
            TestEntity saved = repo.save(new TestEntity("Test"));
            
            Optional<TestEntity> found = repo.findById(saved.getId());
            
            assertThat(found).isPresent();
            assertThat(found.get().getName()).isEqualTo("Test");
        }
        
        @Test
        @DisplayName("findAll возвращает все сущности")
        void shouldFindAll() {
            var repo = createRepository();
            repo.save(new TestEntity("A"));
            repo.save(new TestEntity("B"));
            
            List<TestEntity> all = repo.findAll();
            
            assertThat(all).hasSize(2);
        }
        
        @Test
        @DisplayName("findAll с предикатом фильтрует сущности")
        void shouldFindAllWithPredicate() {
            var repo = createRepository();
            repo.save(new TestEntity("Alice"));
            repo.save(new TestEntity("Bob"));
            repo.save(new TestEntity("Anna"));
            
            List<TestEntity> filtered = repo.findAll(e -> e.getName().startsWith("A"));
            
            assertThat(filtered).hasSize(2);
        }
        
        @Test
        @DisplayName("delete удаляет сущность")
        void shouldDeleteEntity() {
            var repo = createRepository();
            TestEntity saved = repo.save(new TestEntity("Test"));
            
            repo.delete(saved);
            
            assertThat(repo.existsById(saved.getId())).isFalse();
        }
    }
}

