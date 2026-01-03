package com.java.learning.generics;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * In-memory реализация Repository.
 * 
 * TODO: Реализуй этот класс
 * 
 * @param <T> тип сущности, должен реализовывать Identifiable
 * @param <ID> тип идентификатора
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID> implements Repository<T, ID> {
    
    // TODO: Добавь поле для хранения сущностей (Map<ID, T>)
    // TODO: Добавь функцию генерации ID
    
    /**
     * Создаёт репозиторий с генератором ID.
     * 
     * @param idGenerator функция генерации нового ID
     */
    public InMemoryRepository(Function<T, ID> idGenerator) {
        // TODO: Реализуй конструктор
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public T save(T entity) {
        // TODO: Реализуй метод
        // Если entity.getId() == null, сгенерируй новый ID
        // Сохрани сущность в Map
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        // TODO: Реализуй метод
        // Подсказка: используй StreamSupport для итерации
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public Optional<T> findById(ID id) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public List<T> findAll() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public List<T> findAll(Predicate<? super T> predicate) {
        // TODO: Реализуй метод
        // Подсказка: почему Predicate<? super T>, а не Predicate<T>?
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public boolean existsById(ID id) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void delete(T entity) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void deleteById(ID id) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public long count() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
}

