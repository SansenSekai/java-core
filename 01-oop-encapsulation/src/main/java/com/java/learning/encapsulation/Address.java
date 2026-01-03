package com.java.learning.encapsulation;

/**
 * Immutable класс, представляющий адрес.
 * 
 * После создания объекта его состояние не может быть изменено.
 * 
 * TODO: Реализуй этот класс как immutable
 */
public final class Address {
    
    // TODO: Определи поля (все должны быть final)
    private final String street;
    private final String city;
    private final String zipCode;
    private final String country;
    
    /**
     * Создаёт новый адрес.
     * 
     * @param street улица и номер дома
     * @param city город
     * @param zipCode почтовый индекс
     * @param country страна
     * @throws IllegalArgumentException если любой аргумент null или пустой
     */
    public Address(String street, String city, String zipCode, String country) {
        // TODO: Реализуй валидацию аргументов
        // - Проверь все аргументы на null и пустоту
        // - Выбрось IllegalArgumentException с понятным сообщением
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    public String getStreet() {
        // TODO: Убедись, что возвращается корректное значение
        return street;
    }
    
    public String getCity() {
        // TODO: Убедись, что возвращается корректное значение
        return city;
    }
    
    public String getZipCode() {
        // TODO: Убедись, что возвращается корректное значение
        return zipCode;
    }
    
    public String getCountry() {
        // TODO: Убедись, что возвращается корректное значение
        return country;
    }
    
    /**
     * Создаёт копию адреса с изменённым городом.
     * 
     * Это паттерн "wither" для immutable объектов:
     * вместо изменения создаём новый объект.
     */
    public Address withCity(String newCity) {
        // TODO: Реализуй метод правильно
        throw new UnsupportedOperationException("TODO: implement withCity");
    }
    
    /**
     * Создаёт копию адреса с изменённой улицей.
     */
    public Address withStreet(String newStreet) {
        // TODO: Реализуй метод правильно
        throw new UnsupportedOperationException("TODO: implement withStreet");
    }
    
    // TODO: Реализуй equals(), hashCode() и toString()
    // Подсказка: используй Objects.equals(), Objects.hash()
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй правильное сравнение по всем полям
        throw new UnsupportedOperationException("TODO: implement equals");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode, консистентный с equals
        throw new UnsupportedOperationException("TODO: implement hashCode");
    }
    
    @Override
    public String toString() {
        // TODO: Реализуй читаемое строковое представление
        throw new UnsupportedOperationException("TODO: implement toString");
    }
}
