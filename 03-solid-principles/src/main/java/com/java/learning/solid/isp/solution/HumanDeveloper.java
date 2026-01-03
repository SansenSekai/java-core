package com.java.learning.solid.isp.solution;

/**
 * Человек-разработчик — реализует все подходящие интерфейсы.
 * 
 * TODO: Реализуй этот класс
 */
public class HumanDeveloper implements Workable, Feedable, Collaboratable, Programmer {
    
    private final String name;
    
    public HumanDeveloper(String name) {
        this.name = name;
    }
    
    @Override
    public void work() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void eat() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void sleep() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void attendMeeting() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void writeCode() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public void reviewCode() {
        // TODO: Реализуй метод
        throw new UnsupportedOperationException("TODO: implement");
    }
}

