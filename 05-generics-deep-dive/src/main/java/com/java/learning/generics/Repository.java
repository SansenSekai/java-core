package com.java.learning.generics;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Обобщённый репозиторий для работы с сущностями.
 * 
 * @param <T> тип сущности
 * @param <ID> тип идентификатора
 */
public interface Repository<T, ID> {
    
    /**
     * Сохраняет сущность.
     * 
     * @param entity сущность для сохранения
     * @return сохранённая сущность (может иметь сгенерированный ID)
     */
    T save(T entity);
    
    /**
     * Сохраняет несколько сущностей.
     * 
     * @param entities сущности для сохранения
     * @param <S> подтип сущности (для ковариантности)
     * @return список сохранённых сущностей
     */
    <S extends T> List<S> saveAll(Iterable<S> entities);
    
    /**
     * Находит сущность по идентификатору.
     * 
     * @param id идентификатор
     * @return Optional с сущностью или пустой Optional
     */
    Optional<T> findById(ID id);
    
    /**
     * Возвращает все сущности.
     */
    List<T> findAll();
    
    /**
     * Возвращает сущности, удовлетворяющие условию.
     * 
     * @param predicate условие фильтрации
     * @return отфильтрованный список
     */
    List<T> findAll(Predicate<? super T> predicate);
    
    /**
     * Проверяет существование сущности.
     */
    boolean existsById(ID id);
    
    /**
     * Удаляет сущность.
     */
    void delete(T entity);
    
    /**
     * Удаляет сущность по идентификатору.
     */
    void deleteById(ID id);
    
    /**
     * Возвращает количество сущностей.
     */
    long count();
}

