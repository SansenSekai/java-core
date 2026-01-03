package com.java.learning.solid.isp.problem;

/**
 * ПРОБЛЕМА: Robot вынужден реализовывать методы, которые ему не нужны.
 */
public class Robot implements Worker {
    
    @Override
    public void work() {
        System.out.println("Robot working...");
    }
    
    @Override
    public void eat() {
        // Роботы не едят!
        throw new UnsupportedOperationException("Robots don't eat");
    }
    
    @Override
    public void sleep() {
        // Роботы не спят!
        throw new UnsupportedOperationException("Robots don't sleep");
    }
    
    @Override
    public void attendMeeting() {
        // Роботы не ходят на встречи (пока)
        throw new UnsupportedOperationException("Robots don't attend meetings");
    }
    
    @Override
    public void manageProject() {
        // Роботы не управляют проектами
        throw new UnsupportedOperationException("Robots don't manage projects");
    }
    
    @Override
    public void writeCode() {
        System.out.println("Robot generating code...");
    }
    
    @Override
    public void reviewCode() {
        System.out.println("Robot analyzing code...");
    }
}

