package com.java.learning.inheritance;

/**
 * Треугольник — конкретная реализация Shape.
 * 
 * TODO: Реализуй этот класс
 */
public class Triangle extends Shape {
    
    // TODO: Добавь поля для трёх сторон (a, b, c)
    
    /**
     * Создаёт треугольник с указанными сторонами.
     * 
     * @param a первая сторона (> 0)
     * @param b вторая сторона (> 0)
     * @param c третья сторона (> 0)
     * @throws IllegalArgumentException если любая сторона <= 0
     * @throws IllegalArgumentException если стороны не могут образовать треугольник
     */
    public Triangle(double a, double b, double c) {
        // TODO: Реализуй конструктор
        // ВАЖНО: Проверь неравенство треугольника!
        // Сумма любых двух сторон должна быть больше третьей
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getSideA() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getSideB() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    public double getSideC() {
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double area() {
        // TODO: Реализуй вычисление площади
        // Используй формулу Герона:
        // s = (a + b + c) / 2  (полупериметр)
        // area = sqrt(s * (s-a) * (s-b) * (s-c))
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public double perimeter() {
        // TODO: Реализуй вычисление периметра
        // Формула: a + b + c
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String getName() {
        return "Triangle";
    }
    
    /**
     * Проверяет, является ли треугольник равносторонним.
     */
    public boolean isEquilateral() {
        // TODO: Реализуй проверку (все стороны равны)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, является ли треугольник равнобедренным.
     */
    public boolean isIsosceles() {
        // TODO: Реализуй проверку (хотя бы две стороны равны)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    /**
     * Проверяет, является ли треугольник прямоугольным.
     */
    public boolean isRightAngled() {
        // TODO: Реализуй проверку (теорема Пифагора)
        // a² + b² = c² (или любая другая комбинация)
        // Подсказка: найди самую длинную сторону
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй equals
        // Два треугольника равны, если у них одинаковые стороны
        // (порядок сторон может быть разным!)
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        // Подсказка: сортировка сторон поможет с equals
        throw new UnsupportedOperationException("TODO: implement");
    }
}

