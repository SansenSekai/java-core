package com.java.learning.patterns.creational;

import java.util.*;

/**
 * Паттерн Builder — пошаговое создание сложных объектов.
 * 
 * Используй когда:
 * - Много параметров конструктора
 * - Есть опциональные параметры
 * - Нужна валидация при создании
 * - Объект должен быть immutable
 * 
 * TODO: Реализуй Builder для класса HttpRequest
 */
public class Builder {
    
    /**
     * Пример: HTTP запрос с множеством опциональных параметров.
     */
    public static class HttpRequest {
        
        // Обязательные поля
        private final String method;
        private final String url;
        
        // Опциональные поля
        private final Map<String, String> headers;
        private final String body;
        private final int timeout;
        private final boolean followRedirects;
        
        private HttpRequest(HttpRequestBuilder builder) {
            this.method = builder.method;
            this.url = builder.url;
            this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
            this.body = builder.body;
            this.timeout = builder.timeout;
            this.followRedirects = builder.followRedirects;
        }
        
        // Геттеры
        public String getMethod() { return method; }
        public String getUrl() { return url; }
        public Map<String, String> getHeaders() { return headers; }
        public String getBody() { return body; }
        public int getTimeout() { return timeout; }
        public boolean isFollowRedirects() { return followRedirects; }
        
        /**
         * Статический метод для создания Builder.
         */
        public static HttpRequestBuilder builder(String method, String url) {
            return new HttpRequestBuilder(method, url);
        }
    }
    
    /**
     * Builder для HttpRequest.
     * 
     * TODO: Реализуй все методы
     */
    public static class HttpRequestBuilder {
        
        // Обязательные
        private final String method;
        private final String url;
        
        // Опциональные с дефолтами
        private Map<String, String> headers = new HashMap<>();
        private String body;
        private int timeout = 30_000;
        private boolean followRedirects = true;
        
        public HttpRequestBuilder(String method, String url) {
            // TODO: Валидация
            this.method = method;
            this.url = url;
        }
        
        public HttpRequestBuilder header(String name, String value) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public HttpRequestBuilder headers(Map<String, String> headers) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public HttpRequestBuilder body(String body) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public HttpRequestBuilder timeout(int timeoutMs) {
            // TODO: Реализуй с валидацией (> 0)
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public HttpRequestBuilder followRedirects(boolean follow) {
            // TODO: Реализуй
            throw new UnsupportedOperationException("TODO: implement");
        }
        
        public HttpRequest build() {
            // TODO: Финальная валидация и создание объекта
            throw new UnsupportedOperationException("TODO: implement");
        }
    }
}

