package com.java.learning.solid.lsp.solution;

/**
 * Immutable Square — отдельный класс, не наследует Rectangle.
 * 
 * TODO: Реализуй этот класс
 */
public final class ImmutableSquare implements Shape {
    
    // TODO: Добавь final поле side
    
    public ImmutableSquare(int side) {
        // TODO: Реализуй конструктор
        // Проверь, что side > 0
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public int getSide() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новый квадрат с изменённой стороной.
     */
    public ImmutableSquare withSide(int newSide) {
        // TODO: Реализуй wither-метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int getArea() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int getPerimeter() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
}

