package com.java.learning.solid.dip.problem;

/**
 * Конкретная реализация email-клиента.
 */
public class SmtpEmailClient {
    
    public void sendEmail(String to, String subject, String body) {
        // Эмуляция отправки email через SMTP
        System.out.println("Sending email via SMTP to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

