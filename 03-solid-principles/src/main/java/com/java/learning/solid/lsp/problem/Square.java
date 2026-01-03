package com.java.learning.solid.lsp.problem;

/**
 * ПРОБЛЕМА: Square переопределяет setWidth/setHeight так,
 * что они связаны. Это нарушает контракт Rectangle.
 * 
 * Пример нарушения LSP:
 * 
 * void resizeRectangle(Rectangle r) {
 *     r.setWidth(5);
 *     r.setHeight(10);
 *     assert r.getArea() == 50;  // FAIL для Square!
 * }
 */
public class Square extends Rectangle {
    
    public Square(int side) {
        super(side, side);
    }
    
    @Override
    public void setWidth(int width) {
        // Нарушение: изменяем и height тоже
        super.setWidth(width);
        super.setHeight(width);
    }
    
    @Override
    public void setHeight(int height) {
        // Нарушение: изменяем и width тоже
        super.setWidth(height);
        super.setHeight(height);
    }
}

