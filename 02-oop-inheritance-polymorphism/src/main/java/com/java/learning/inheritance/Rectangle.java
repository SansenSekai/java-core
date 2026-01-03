package com.java.learning.inheritance;

/**
 * Прямоугольник — конкретная реализация Shape.
 * 
 * TODO: Реализуй этот класс
 */
public class Rectangle extends Shape {
    
    // TODO: Добавь поля для ширины и высоты
    
    /**
     * Создаёт прямоугольник с указанными размерами.
     * 
     * @param width ширина (должна быть > 0)
     * @param height высота (должна быть > 0)
     * @throws IllegalArgumentException если width <= 0 или height <= 0
     */
    public Rectangle(double width, double height) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getWidth() {
        // TODO: Реализуй геттер
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getHeight() {
        // TODO: Реализуй геттер
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double area() {
        // TODO: Реализуй вычисление площади
        // Формула: width * height
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double perimeter() {
        // TODO: Реализуй вычисление периметра
        // Формула: 2 * (width + height)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        return "Rectangle";
    }
    
    /**
     * Проверяет, является ли прямоугольник квадратом.
     */
    public boolean isSquare() {
        // TODO: Реализуй проверку
        // Подсказка: используй Double.compare() для сравнения double
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй equals
        // Два прямоугольника равны, если у них одинаковые размеры
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        throw new UnsupportedOperationException("TODO: implement");
    }
}

