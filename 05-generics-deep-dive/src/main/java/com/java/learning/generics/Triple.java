package com.java.learning.generics;

/**
 * Immutable тройка значений.
 * 
 * TODO: Реализуй этот класс
 * 
 * @param <A> тип первого элемента
 * @param <B> тип второго элемента
 * @param <C> тип третьего элемента
 */
public final class Triple<A, B, C> {
    
    // TODO: Добавь поля
    
    private Triple(A first, B second, C third) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public static <A, B, C> Triple<A, B, C> of(A first, B second, C third) {
        // TODO: Реализуй фабричный метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public A getFirst() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public B getSecond() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public C getThird() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразует в Pair из первых двух элементов.
     */
    public Pair<A, B> toFirstPair() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Преобразует в Pair из последних двух элементов.
     */
    public Pair<B, C> toLastPair() {
        // TODO: Реализуй метод
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
        // Формат: "(first, second, third)"
        throw new UnsupportedOperationException("TODO: implement");
    }
}

