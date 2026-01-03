package com.java.learning.inheritance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Shape Hierarchy: полиморфизм и наследование")
class ShapeTest {
    
    @Nested
    @DisplayName("Circle")
    class CircleTests {
        
        @Test
        @DisplayName("Вычисление площади круга")
        void shouldCalculateArea() {
            Circle circle = new Circle(5.0);
            
            assertThat(circle.area()).isCloseTo(78.54, within(0.01));
        }
        
        @Test
        @DisplayName("Вычисление периметра круга")
        void shouldCalculatePerimeter() {
            Circle circle = new Circle(5.0);
            
            assertThat(circle.perimeter()).isCloseTo(31.42, within(0.01));
        }
        
        @Test
        @DisplayName("Нельзя создать круг с нулевым радиусом")
        void shouldRejectZeroRadius() {
            assertThatThrownBy(() -> new Circle(0))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Нельзя создать круг с отрицательным радиусом")
        void shouldRejectNegativeRadius() {
            assertThatThrownBy(() -> new Circle(-5))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Два круга с одинаковым радиусом равны")
        void shouldBeEqualForSameRadius() {
            Circle c1 = new Circle(5.0);
            Circle c2 = new Circle(5.0);
            
            assertThat(c1).isEqualTo(c2);
            assertThat(c1.hashCode()).isEqualTo(c2.hashCode());
        }
    }
    
    @Nested
    @DisplayName("Rectangle")
    class RectangleTests {
        
        @Test
        @DisplayName("Вычисление площади прямоугольника")
        void shouldCalculateArea() {
            Rectangle rect = new Rectangle(4.0, 5.0);
            
            assertThat(rect.area()).isEqualTo(20.0);
        }
        
        @Test
        @DisplayName("Вычисление периметра прямоугольника")
        void shouldCalculatePerimeter() {
            Rectangle rect = new Rectangle(4.0, 5.0);
            
            assertThat(rect.perimeter()).isEqualTo(18.0);
        }
        
        @Test
        @DisplayName("Определение квадрата")
        void shouldIdentifySquare() {
            Rectangle square = new Rectangle(5.0, 5.0);
            Rectangle rect = new Rectangle(4.0, 5.0);
            
            assertThat(square.isSquare()).isTrue();
            assertThat(rect.isSquare()).isFalse();
        }
        
        @Test
        @DisplayName("Нельзя создать прямоугольник с нулевой стороной")
        void shouldRejectZeroSide() {
            assertThatThrownBy(() -> new Rectangle(0, 5))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Rectangle(5, 0))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    @Nested
    @DisplayName("Triangle")
    class TriangleTests {
        
        @Test
        @DisplayName("Вычисление площади треугольника")
        void shouldCalculateArea() {
            Triangle triangle = new Triangle(3, 4, 5);
            
            assertThat(triangle.area()).isCloseTo(6.0, within(0.01));
        }
        
        @Test
        @DisplayName("Вычисление периметра треугольника")
        void shouldCalculatePerimeter() {
            Triangle triangle = new Triangle(3, 4, 5);
            
            assertThat(triangle.perimeter()).isEqualTo(12.0);
        }
        
        @Test
        @DisplayName("Нельзя создать треугольник с нарушением неравенства треугольника")
        void shouldRejectInvalidTriangle() {
            assertThatThrownBy(() -> new Triangle(1, 2, 10))
                .isInstanceOf(IllegalArgumentException.class);
        }
        
        @Test
        @DisplayName("Определение равностороннего треугольника")
        void shouldIdentifyEquilateralTriangle() {
            Triangle equilateral = new Triangle(5, 5, 5);
            Triangle notEquilateral = new Triangle(3, 4, 5);
            
            assertThat(equilateral.isEquilateral()).isTrue();
            assertThat(notEquilateral.isEquilateral()).isFalse();
        }
        
        @Test
        @DisplayName("Определение равнобедренного треугольника")
        void shouldIdentifyIsoscelesTriangle() {
            Triangle isosceles = new Triangle(5, 5, 8);
            Triangle scalene = new Triangle(3, 4, 5);
            
            assertThat(isosceles.isIsosceles()).isTrue();
            assertThat(scalene.isIsosceles()).isFalse();
        }
        
        @Test
        @DisplayName("Определение прямоугольного треугольника")
        void shouldIdentifyRightAngledTriangle() {
            Triangle rightAngled = new Triangle(3, 4, 5);
            Triangle notRightAngled = new Triangle(3, 4, 6);
            
            assertThat(rightAngled.isRightAngled()).isTrue();
            assertThat(notRightAngled.isRightAngled()).isFalse();
        }
        
        @Test
        @DisplayName("Два треугольника с одинаковыми сторонами равны (независимо от порядка)")
        void shouldBeEqualRegardlessOfSideOrder() {
            Triangle t1 = new Triangle(3, 4, 5);
            Triangle t2 = new Triangle(5, 3, 4);
            Triangle t3 = new Triangle(4, 5, 3);
            
            assertThat(t1).isEqualTo(t2);
            assertThat(t2).isEqualTo(t3);
            assertThat(t1.hashCode()).isEqualTo(t2.hashCode());
        }
    }
    
    @Nested
    @DisplayName("Полиморфное поведение")
    class PolymorphismTests {
        
        @Test
        @DisplayName("Сортировка фигур по площади")
        void shouldSortShapesByArea() {
            List<Shape> shapes = Arrays.asList(
                new Circle(10),           // area ≈ 314.16
                new Rectangle(5, 5),      // area = 25
                new Triangle(3, 4, 5)     // area = 6
            );
            
            Collections.sort(shapes);
            
            assertThat(shapes.get(0).getName()).isEqualTo("Triangle");
            assertThat(shapes.get(1).getName()).isEqualTo("Rectangle");
            assertThat(shapes.get(2).getName()).isEqualTo("Circle");
        }
        
        @Test
        @DisplayName("isLarge работает полиморфно")
        void shouldWorkPolymorphicallyForIsLarge() {
            Shape smallCircle = new Circle(3);     // area ≈ 28.27
            Shape largeCircle = new Circle(10);    // area ≈ 314.16
            Shape largeRect = new Rectangle(20, 10); // area = 200
            
            assertThat(smallCircle.isLarge()).isFalse();
            assertThat(largeCircle.isLarge()).isTrue();
            assertThat(largeRect.isLarge()).isTrue();
        }
        
        @Test
        @DisplayName("toString работает для всех фигур")
        void shouldGenerateToStringForAllShapes() {
            Shape circle = new Circle(5);
            Shape rect = new Rectangle(4, 6);
            Shape triangle = new Triangle(3, 4, 5);
            
            assertThat(circle.toString()).contains("Circle");
            assertThat(rect.toString()).contains("Rectangle");
            assertThat(triangle.toString()).contains("Triangle");
        }
    }
    
    @Nested
    @DisplayName("Фигуры разных типов")
    class DifferentShapeTypesTests {
        
        @Test
        @DisplayName("Круг не равен прямоугольнику даже при одинаковой площади")
        void circleIsNotEqualToRectangle() {
            Circle circle = new Circle(Math.sqrt(25 / Math.PI));  // area = 25
            Rectangle rect = new Rectangle(5, 5);                  // area = 25
            
            assertThat(circle.area()).isCloseTo(rect.area(), within(0.01));
            assertThat(circle).isNotEqualTo(rect);
        }
    }
}

