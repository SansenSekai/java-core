package com.java.learning.solid.dip.solution;

/**
 * SMS-реализация MessageSender.
 * 
 * TODO: Реализуй этот класс
 */
public class SmsSender implements MessageSender {
    
    @Override
    public boolean send(String recipient, String subject, String body) {
        // TODO: Реализуй отправку SMS (эмуляция)
        // SMS не имеет subject, можно игнорировать или добавить в начало body
        throw new UnsupportedOperationException("TODO: implement");
    }
}

