package com.java.learning.collections;

import java.util.List;

/**
 * Trie (Prefix Tree) - дерево для эффективного хранения и поиска строк.
 * 
 * Особенности:
 * - Поиск по префиксу за O(m), где m - длина префикса
 * - Эффективное хранение строк с общими префиксами
 * - Автодополнение
 * 
 * Пример структуры для слов "cat", "car", "card":
 * 
 *        root
 *          |
 *          c
 *          |
 *          a
 *         / \
 *        t   r(*)
 *       (*)   |
 *             d(*)
 * 
 * (*) - отмечает конец слова
 * 
 * TODO: Реализуй Trie
 */
public class Trie {
    
    // TODO: Определи внутренний класс TrieNode
    // Подсказка: Map<Character, TrieNode> children + boolean isEndOfWord
    
    /**
     * Добавляет слово в Trie.
     * 
     * @param word слово (не null, не пустое)
     */
    public void insert(String word) {
        // TODO: Реализуй метод
        // Пройди по каждому символу, создавая узлы при необходимости
        throw new UnsupportedOperationException("TODO: implement insert");
    }
    
    /**
     * Проверяет, есть ли точное совпадение слова.
     * 
     * @param word слово для поиска
     * @return true если слово есть в Trie
     */
    public boolean search(String word) {
        // TODO: Реализуй метод
        // Пройди по символам, проверь isEndOfWord в конце
        throw new UnsupportedOperationException("TODO: implement search");
    }
    
    /**
     * Проверяет, есть ли слова с данным префиксом.
     * 
     * @param prefix префикс
     * @return true если есть хотя бы одно слово с этим префиксом
     */
    public boolean startsWith(String prefix) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement startsWith");
    }
    
    /**
     * Возвращает все слова с заданным префиксом.
     * 
     * @param prefix префикс
     * @param limit максимальное количество результатов
     * @return список слов
     */
    public List<String> autocomplete(String prefix, int limit) {
        // TODO: Реализуй метод
        // 1. Найди узел для prefix
        // 2. Собери все слова из поддерева (DFS)
        throw new UnsupportedOperationException("TODO: implement autocomplete");
    }
    
    /**
     * Удаляет слово из Trie.
     * 
     * @param word слово для удаления
     * @return true если слово было удалено
     */
    public boolean delete(String word) {
        // TODO: Реализуй метод
        // Подсказка: нужно рекурсивно удалять ненужные узлы
        throw new UnsupportedOperationException("TODO: implement delete");
    }
    
    /**
     * Возвращает количество слов в Trie.
     */
    public int size() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement size");
    }
}

