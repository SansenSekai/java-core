package com.java.learning.reflection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Reflection Utils")
class ReflectionUtilsTest {

    // Тестовые классы
    static class Source {
        private String name = "test";
        private int age = 25;
        public String publicField = "public";
    }
    
    static class Target {
        private String name;
        private int age;
        public String publicField;
        
        public String getName() { return name; }
        public int getAge() { return age; }
    }
    
    @Nested
    @DisplayName("createInstance")
    class CreateInstance {
        
        @Test
        @DisplayName("Создаёт объект по имени класса")
        void shouldCreateInstanceByClassName() throws Exception {
            Object list = ReflectionUtils.createInstance("java.util.ArrayList");
            
            assertThat(list).isInstanceOf(java.util.ArrayList.class);
        }
        
        @Test
        @DisplayName("Создаёт объект с аргументами конструктора")
        void shouldCreateInstanceWithArgs() throws Exception {
            Object str = ReflectionUtils.createInstance(
                "java.lang.String", 
                "hello"
            );
            
            assertThat(str).isEqualTo("hello");
        }
    }
    
    @Nested
    @DisplayName("copyFields")
    class CopyFields {
        
        @Test
        @DisplayName("Копирует поля с одинаковыми именами")
        void shouldCopyMatchingFields() throws Exception {
            Source source = new Source();
            Target target = new Target();
            
            ReflectionUtils.copyFields(source, target);
            
            assertThat(target.getName()).isEqualTo("test");
            assertThat(target.getAge()).isEqualTo(25);
            assertThat(target.publicField).isEqualTo("public");
        }
    }
    
    @Nested
    @DisplayName("getAllMethods")
    class GetAllMethods {
        
        @Test
        @DisplayName("Возвращает все методы включая унаследованные")
        void shouldReturnAllMethods() {
            List<Method> methods = ReflectionUtils.getAllMethods(Target.class);
            
            List<String> methodNames = methods.stream()
                .map(Method::getName)
                .toList();
            
            assertThat(methodNames).contains("getName", "getAge");
            // Также должны быть методы Object: equals, hashCode, toString
            assertThat(methodNames).contains("equals", "hashCode", "toString");
        }
    }
    
    @Nested
    @DisplayName("Приватные поля и методы")
    class PrivateAccess {
        
        @Test
        @DisplayName("Читает приватное поле")
        void shouldGetPrivateField() throws Exception {
            Source source = new Source();
            
            Object value = ReflectionUtils.getPrivateField(source, "name");
            
            assertThat(value).isEqualTo("test");
        }
        
        @Test
        @DisplayName("Устанавливает приватное поле")
        void shouldSetPrivateField() throws Exception {
            Source source = new Source();
            
            ReflectionUtils.setPrivateField(source, "name", "changed");
            
            assertThat(ReflectionUtils.getPrivateField(source, "name"))
                .isEqualTo("changed");
        }
    }
    
    @Nested
    @DisplayName("isImmutable")
    class IsImmutable {
        
        final class ImmutableClass {
            private final String value;
            ImmutableClass(String value) { this.value = value; }
            public String getValue() { return value; }
        }
        
        static class MutableClass {
            private String value;
            public void setValue(String v) { this.value = v; }
        }
        
        @Test
        @DisplayName("Определяет immutable класс")
        void shouldDetectImmutable() {
            assertThat(ReflectionUtils.isImmutable(String.class)).isTrue();
            assertThat(ReflectionUtils.isImmutable(Integer.class)).isTrue();
        }
        
        @Test
        @DisplayName("Определяет mutable класс")
        void shouldDetectMutable() {
            assertThat(ReflectionUtils.isImmutable(MutableClass.class)).isFalse();
        }
    }
}

