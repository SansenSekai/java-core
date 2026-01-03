package com.java.learning.generics;

/**
 * Pair, который можно сравнивать, если оба элемента Comparable.
 * 
 * Сравнение: сначала по первому элементу, затем по второму.
 * 
 * TODO: Реализуй этот класс
 * 
 * @param <K> тип первого элемента (Comparable)
 * @param <V> тип второго элемента (Comparable)
 */
public final class ComparablePair<K extends Comparable<? super K>, V extends Comparable<? super V>> 
        implements Comparable<ComparablePair<K, V>> {
    
    // TODO: Добавь поля
    
    private ComparablePair(K first, V second) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public static <K extends Comparable<? super K>, V extends Comparable<? super V>> 
            ComparablePair<K, V> of(K first, V second) {
        // TODO: Реализуй фабричный метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public K getFirst() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public V getSecond() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int compareTo(ComparablePair<K, V> other) {
        // TODO: Реализуй сравнение
        // Сначала по first, затем по second
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}

