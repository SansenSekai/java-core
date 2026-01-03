package com.java.learning.inheritance;

/**
 * Круг — конкретная реализация Shape.
 * 
 * TODO: Реализуй этот класс
 */
public class Circle extends Shape {
    
    // TODO: Добавь поле для радиуса
    
    /**
     * Создаёт круг с указанным радиусом.
     * 
     * @param radius радиус круга (должен быть > 0)
     * @throws IllegalArgumentException если radius <= 0
     */
    public Circle(double radius) {
        // TODO: Реализуй конструктор
        // - Проверь, что radius > 0
        // - Сохрани радиус
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getRadius() {
        // TODO: Реализуй геттер
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double area() {
        // TODO: Реализуй вычисление площади
        // Формула: π * r²
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double perimeter() {
        // TODO: Реализуй вычисление периметра (длины окружности)
        // Формула: 2 * π * r
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        return "Circle";
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй equals
        // Два круга равны, если у них одинаковый радиус
        // Подсказка: используй Double.compare() для сравнения double
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        // Подсказка: Double.hashCode(radius)
        throw new UnsupportedOperationException("TODO: implement");
    }
}

