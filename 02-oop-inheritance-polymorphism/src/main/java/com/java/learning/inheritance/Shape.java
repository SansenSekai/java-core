package com.java.learning.inheritance;

/**
 * Абстрактный базовый класс для геометрических фигур.
 * 
 * Демонстрирует:
 * - Абстрактные методы
 * - Полиморфизм через наследование
 * - Реализацию Comparable
 * 
 * TODO: Реализуй этот класс согласно требованиям
 */
public abstract class Shape implements Comparable<Shape> {
    
    /**
     * Вычисляет площадь фигуры.
     * 
     * @return площадь фигуры (всегда > 0)
     */
    public abstract double area();
    
    /**
     * Вычисляет периметр фигуры.
     * 
     * @return периметр фигуры (всегда > 0)
     */
    public abstract double perimeter();
    
    /**
     * Возвращает название фигуры.
     * 
     * @return название (например, "Circle", "Rectangle")
     */
    public abstract String getName();
    
    /**
     * Сравнивает фигуры по площади.
     * 
     * @param other другая фигура
     * @return отрицательное число, если this < other; 0, если равны; положительное, если this > other
     */
    @Override
    public int compareTo(Shape other) {
        // TODO: Реализуй сравнение по площади
        // Подсказка: используй Double.compare()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, является ли фигура «большой» (площадь > 100).
     * 
     * Это пример конкретного метода в абстрактном классе,
     * который использует абстрактный метод.
     */
    public boolean isLarge() {
        return area() > 100;
    }
    
    @Override
    public String toString() {
        return String.format("%s [area=%.2f, perimeter=%.2f]", 
            getName(), area(), perimeter());
    }
}

