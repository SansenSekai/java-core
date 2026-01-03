package com.java.learning.solid.lsp.problem;

/**
 * ПРОБЛЕМА: классическое нарушение LSP с Rectangle/Square.
 * 
 * Square наследует Rectangle и переопределяет setters так,
 * что код, работающий с Rectangle, ломается при работе с Square.
 */
public class Rectangle {
    
    protected int width;
    protected int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

