package com.java.learning.generics;

/**
 * Интерфейс для сущностей с идентификатором.
 * 
 * @param <ID> тип идентификатора
 */
public interface Identifiable<ID> {
    
    /**
     * Возвращает идентификатор сущности.
     */
    ID getId();
    
    /**
     * Устанавливает идентификатор сущности.
     */
    void setId(ID id);
}

