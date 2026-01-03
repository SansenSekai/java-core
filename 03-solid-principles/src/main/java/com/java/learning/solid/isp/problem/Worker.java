package com.java.learning.solid.isp.problem;

/**
 * ПРОБЛЕМА: «жирный» интерфейс.
 * 
 * Не все работники могут делать всё. Например:
 * - Роботы не едят и не спят
 * - Удалённые работники не ходят на встречи в офис
 * - Стажёры не управляют проектами
 * 
 * ЗАДАНИЕ: Раздели этот интерфейс в пакете isp.solution
 */
public interface Worker {
    
    void work();
    
    void eat();
    
    void sleep();
    
    void attendMeeting();
    
    void manageProject();
    
    void writeCode();
    
    void reviewCode();
}

