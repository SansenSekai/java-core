package com.java.learning.encapsulation;

import java.time.LocalDate;
import java.util.List;

/**
 * Полностью иммутабельный класс Person.
 * 
 * TODO: Реализуй этот класс
 * 
 * Требования к иммутабельности:
 * 1. Класс final — нельзя создать мутабельный подкласс
 * 2. Все поля final — нельзя переприсвоить
 * 3. Нет сеттеров — нельзя изменить напрямую
 * 4. Defensive copy для мутабельных объектов (List, Date, etc.)
 * 5. Геттеры возвращают копии или immutable wrapper'ы
 */
public final class Person {
    
    // TODO: Определи final поля
    private final String name;
    private final LocalDate birthDate;
    private final List<String> hobbies;
    private final Address address;
    
    /**
     * Создаёт новую персону.
     * 
     * @param name имя (не null, не пустое)
     * @param birthDate дата рождения (не в будущем)
     * @param hobbies список хобби (может быть пустым, но не null)
     * @param address адрес проживания (не null)
     */
    public Person(String name, LocalDate birthDate, List<String> hobbies, Address address) {
        // TODO: Реализуй валидацию и defensive copying
        // Подсказка: List.copyOf() создаёт immutable копию
        this.name = name;
        this.birthDate = birthDate;
        this.hobbies = hobbies;
        this.address = address;
    }
    
    public String getName() {
        // TODO: Проверь реализацию
        return name;
    }
    
    public LocalDate getBirthDate() {
        // TODO: Проверь реализацию (LocalDate immutable, копия не нужна)
        return birthDate;
    }
    
    /**
     * Возвращает неизменяемый список хобби.
     * Попытка модификации вызовет UnsupportedOperationException.
     */
    public List<String> getHobbies() {
        // TODO: Верни неизменяемый список
        throw new UnsupportedOperationException("TODO: implement getHobbies");
    }
    
    public Address getAddress() {
        // TODO: Проверь реализацию (Address должен быть immutable)
        return address;
    }
    
    /**
     * Возвращает возраст в полных годах.
     * 
     * Это computed property — вычисляется динамически.
     */
    public int getAge() {
        // TODO: Реализуй вычисление возраста
        // Подсказка: java.time.Period.between()
        throw new UnsupportedOperationException("TODO: implement getAge");
    }
    
    /**
     * Создаёт новую персону с добавленным хобби.
     * 
     * Паттерн "wither" — вместо изменения создаём новый объект.
     */
    public Person withHobby(String hobby) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement withHobby");
    }
    
    /**
     * Создаёт новую персону с новым адресом.
     */
    public Person withAddress(Address newAddress) {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement withAddress");
    }
    
    // TODO: Реализуй equals(), hashCode()
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй сравнение по всем полям
        throw new UnsupportedOperationException("TODO: implement equals");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        throw new UnsupportedOperationException("TODO: implement hashCode");
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', birthDate=" + birthDate + "}";
    }
}
