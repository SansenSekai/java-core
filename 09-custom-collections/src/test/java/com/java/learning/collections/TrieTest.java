package com.java.learning.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Trie (Prefix Tree)")
class TrieTest {

    private Trie trie;
    
    @BeforeEach
    void setUp() {
        trie = new Trie();
    }
    
    @Nested
    @DisplayName("Вставка и поиск")
    class InsertAndSearch {
        
        @Test
        @DisplayName("Поиск добавленного слова")
        void shouldFindInsertedWord() {
            trie.insert("hello");
            
            assertThat(trie.search("hello")).isTrue();
        }
        
        @Test
        @DisplayName("Поиск несуществующего слова")
        void shouldNotFindMissingWord() {
            trie.insert("hello");
            
            assertThat(trie.search("world")).isFalse();
        }
        
        @Test
        @DisplayName("Префикс не является словом")
        void prefixIsNotAWord() {
            trie.insert("hello");
            
            assertThat(trie.search("hell")).isFalse();
            assertThat(trie.search("hello")).isTrue();
        }
        
        @Test
        @DisplayName("Слова с общим префиксом")
        void wordsWithCommonPrefix() {
            trie.insert("car");
            trie.insert("card");
            trie.insert("care");
            
            assertThat(trie.search("car")).isTrue();
            assertThat(trie.search("card")).isTrue();
            assertThat(trie.search("care")).isTrue();
            assertThat(trie.search("ca")).isFalse();
        }
    }
    
    @Nested
    @DisplayName("startsWith")
    class StartsWithTests {
        
        @Test
        @DisplayName("Находит слова с префиксом")
        void shouldFindWordsWithPrefix() {
            trie.insert("hello");
            trie.insert("help");
            trie.insert("world");
            
            assertThat(trie.startsWith("hel")).isTrue();
            assertThat(trie.startsWith("wor")).isTrue();
            assertThat(trie.startsWith("foo")).isFalse();
        }
        
        @Test
        @DisplayName("Пустой префикс")
        void emptyPrefixMatchesAll() {
            trie.insert("a");
            
            assertThat(trie.startsWith("")).isTrue();
        }
    }
    
    @Nested
    @DisplayName("Автодополнение")
    class Autocomplete {
        
        @Test
        @DisplayName("Возвращает слова с префиксом")
        void shouldReturnWordsWithPrefix() {
            trie.insert("car");
            trie.insert("card");
            trie.insert("care");
            trie.insert("careful");
            trie.insert("dog");
            
            List<String> results = trie.autocomplete("car", 10);
            
            assertThat(results).containsExactlyInAnyOrder("car", "card", "care", "careful");
        }
        
        @Test
        @DisplayName("Ограничивает количество результатов")
        void shouldLimitResults() {
            trie.insert("a1");
            trie.insert("a2");
            trie.insert("a3");
            trie.insert("a4");
            trie.insert("a5");
            
            List<String> results = trie.autocomplete("a", 3);
            
            assertThat(results).hasSize(3);
        }
        
        @Test
        @DisplayName("Пустой результат для несуществующего префикса")
        void shouldReturnEmptyForNoMatch() {
            trie.insert("hello");
            
            List<String> results = trie.autocomplete("xyz", 10);
            
            assertThat(results).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("Удаление")
    class Deletion {
        
        @Test
        @DisplayName("Удаление существующего слова")
        void shouldDeleteExistingWord() {
            trie.insert("hello");
            trie.insert("help");
            
            assertThat(trie.delete("hello")).isTrue();
            assertThat(trie.search("hello")).isFalse();
            assertThat(trie.search("help")).isTrue();
        }
        
        @Test
        @DisplayName("Удаление несуществующего слова")
        void shouldReturnFalseForMissingWord() {
            trie.insert("hello");
            
            assertThat(trie.delete("world")).isFalse();
        }
        
        @Test
        @DisplayName("Удаление префикса не удаляет более длинные слова")
        void deletingPrefixDoesNotAffectLongerWords() {
            trie.insert("car");
            trie.insert("card");
            
            trie.delete("car");
            
            assertThat(trie.search("car")).isFalse();
            assertThat(trie.search("card")).isTrue();
        }
    }
    
    @Nested
    @DisplayName("Размер")
    class Size {
        
        @Test
        @DisplayName("size отслеживает количество слов")
        void shouldTrackWordCount() {
            assertThat(trie.size()).isZero();
            
            trie.insert("a");
            assertThat(trie.size()).isEqualTo(1);
            
            trie.insert("b");
            assertThat(trie.size()).isEqualTo(2);
            
            // Повторная вставка не увеличивает размер
            trie.insert("a");
            assertThat(trie.size()).isEqualTo(2);
        }
        
        @Test
        @DisplayName("size уменьшается при удалении")
        void shouldDecreaseOnDelete() {
            trie.insert("a");
            trie.insert("b");
            
            trie.delete("a");
            
            assertThat(trie.size()).isEqualTo(1);
        }
    }
}

