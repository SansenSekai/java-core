package com.java.learning.solid.isp.solution;

/**
 * Робот-разработчик — реализует ТОЛЬКО подходящие интерфейсы.
 * 
 * Не реализует Feedable (роботы не едят) и Collaboratable (не ходят на встречи).
 * 
 * TODO: Реализуй этот класс
 */
public class RobotDeveloper implements Workable, Programmer {
    
    private final String model;
    
    public RobotDeveloper(String model) {
        this.model = model;
    }
    
    @Override
    public void work() {
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

