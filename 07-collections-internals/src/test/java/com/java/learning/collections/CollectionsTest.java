package com.java.learning.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Collections Internals")
class CollectionsTest {
    
    @Nested
    @DisplayName("SimpleArrayList")
    class SimpleArrayListTests {
        
        @Test
        @DisplayName("Добавление и получение элементов")
        void shouldAddAndGetElements() {
            SimpleArrayList<String> list = new SimpleArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            
            assertThat(list.size()).isEqualTo(3);
            assertThat(list.get(0)).isEqualTo("a");
            assertThat(list.get(1)).isEqualTo("b");
            assertThat(list.get(2)).isEqualTo("c");
        }
        
        @Test
        @DisplayName("Автоматическое расширение")
        void shouldGrowAutomatically() {
            SimpleArrayList<Integer> list = new SimpleArrayList<>(2);
            
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
            
            assertThat(list.size()).isEqualTo(100);
            assertThat(list.capacity()).isGreaterThanOrEqualTo(100);
        }
        
        @Test
        @DisplayName("Удаление по индексу")
        void shouldRemoveByIndex() {
            SimpleArrayList<String> list = new SimpleArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            
            String removed = list.remove(1);
            
            assertThat(removed).isEqualTo("b");
            assertThat(list.size()).isEqualTo(2);
            assertThat(list.get(0)).isEqualTo("a");
            assertThat(list.get(1)).isEqualTo("c");
        }
        
        @Test
        @DisplayName("IndexOutOfBoundsException для невалидного индекса")
        void shouldThrowForInvalidIndex() {
            SimpleArrayList<String> list = new SimpleArrayList<>();
            list.add("a");
            
            assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
        }
        
        @Test
        @DisplayName("Итерация")
        void shouldIterate() {
            SimpleArrayList<String> list = new SimpleArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            
            StringBuilder result = new StringBuilder();
            for (String s : list) {
                result.append(s);
            }
            
            assertThat(result.toString()).isEqualTo("abc");
        }
        
        @Test
        @DisplayName("trimToSize уменьшает ёмкость")
        void trimToSizeShouldReduceCapacity() {
            SimpleArrayList<Integer> list = new SimpleArrayList<>(100);
            list.add(1);
            list.add(2);
            list.add(3);
            
            list.trimToSize();
            
            assertThat(list.capacity()).isEqualTo(3);
        }
    }
    
    @Nested
    @DisplayName("SimpleLinkedList")
    class SimpleLinkedListTests {
        
        @Test
        @DisplayName("addFirst добавляет в начало")
        void shouldAddFirst() {
            SimpleLinkedList<String> list = new SimpleLinkedList<>();
            list.addFirst("c");
            list.addFirst("b");
            list.addFirst("a");
            
            assertThat(list.getFirst()).isEqualTo("a");
            assertThat(list.getLast()).isEqualTo("c");
        }
        
        @Test
        @DisplayName("addLast добавляет в конец")
        void shouldAddLast() {
            SimpleLinkedList<String> list = new SimpleLinkedList<>();
            list.addLast("a");
            list.addLast("b");
            list.addLast("c");
            
            assertThat(list.getFirst()).isEqualTo("a");
            assertThat(list.getLast()).isEqualTo("c");
        }
        
        @Test
        @DisplayName("removeFirst удаляет первый")
        void shouldRemoveFirst() {
            SimpleLinkedList<String> list = new SimpleLinkedList<>();
            list.add("a");
            list.add("b");
            
            String removed = list.removeFirst();
            
            assertThat(removed).isEqualTo("a");
            assertThat(list.size()).isEqualTo(1);
            assertThat(list.getFirst()).isEqualTo("b");
        }
        
        @Test
        @DisplayName("get работает с оптимизацией")
        void shouldGetByIndex() {
            SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            
            assertThat(list.get(0)).isEqualTo(0);
            assertThat(list.get(5)).isEqualTo(5);
            assertThat(list.get(9)).isEqualTo(9);
        }
        
        @Test
        @DisplayName("NoSuchElementException для пустого списка")
        void shouldThrowForEmptyList() {
            SimpleLinkedList<String> list = new SimpleLinkedList<>();
            
            assertThatThrownBy(list::getFirst)
                .isInstanceOf(NoSuchElementException.class);
        }
    }
}

