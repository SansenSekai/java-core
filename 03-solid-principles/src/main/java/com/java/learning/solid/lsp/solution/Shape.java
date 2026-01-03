package com.java.learning.solid.lsp.solution;

/**
 * Интерфейс для фигур — общий контракт.
 * 
 * Решение проблемы LSP: вместо наследования Square от Rectangle,
 * оба класса реализуют общий интерфейс.
 */
public interface Shape {
    
    /**
     * Вычисляет площадь фигуры.
     */
    int getArea();
    
    /**
     * Вычисляет периметр фигуры.
     */
    int getPerimeter();
}

