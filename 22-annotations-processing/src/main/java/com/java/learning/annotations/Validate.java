package com.java.learning.annotations;

import java.lang.annotation.*;

/**
 * Аннотация для валидации полей.
 * 
 * Пример использования:
 * <pre>
 * public class User {
 *     @Validate(notNull = true, minLength = 3)
 *     private String username;
 *     
 *     @Validate(min = 18, max = 120)
 *     private int age;
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validate {
    
    /**
     * Поле не должно быть null.
     */
    boolean notNull() default false;
    
    /**
     * Минимальная длина строки.
     */
    int minLength() default 0;
    
    /**
     * Максимальная длина строки.
     */
    int maxLength() default Integer.MAX_VALUE;
    
    /**
     * Регулярное выражение для проверки строки.
     */
    String pattern() default "";
    
    /**
     * Минимальное числовое значение.
     */
    long min() default Long.MIN_VALUE;
    
    /**
     * Максимальное числовое значение.
     */
    long max() default Long.MAX_VALUE;
    
    /**
     * Сообщение об ошибке.
     */
    String message() default "Validation failed";
}

