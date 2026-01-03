package com.java.learning.solid.lsp.solution;

/**
 * Immutable Rectangle — решает проблему LSP.
 * 
 * Поскольку объект нельзя изменить после создания,
 * нет проблемы с переопределением setters.
 * 
 * TODO: Реализуй этот класс
 */
public final class ImmutableRectangle implements Shape {
    
    // TODO: Добавь final поля width и height
    
    public ImmutableRectangle(int width, int height) {
        // TODO: Реализуй конструктор
        // Проверь, что width > 0 и height > 0
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public int getWidth() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public int getHeight() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новый прямоугольник с изменённой шириной.
     */
    public ImmutableRectangle withWidth(int newWidth) {
        // TODO: Реализуй wither-метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Создаёт новый прямоугольник с изменённой высотой.
     */
    public ImmutableRectangle withHeight(int newHeight) {
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

