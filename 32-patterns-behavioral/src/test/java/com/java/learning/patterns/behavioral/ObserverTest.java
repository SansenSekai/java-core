package com.java.learning.patterns.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Observer Pattern")
class ObserverTest {

    private Observer.EventPublisher<String> publisher;
    
    @BeforeEach
    void setUp() {
        publisher = new Observer.EventPublisher<>();
    }
    
    @Nested
    @DisplayName("Подписка и публикация")
    class SubscribeAndPublish {
        
        @Test
        @DisplayName("Подписчик получает события")
        void subscriberShouldReceiveEvents() {
            List<String> received = new ArrayList<>();
            
            publisher.subscribe("user.created", event -> received.add(event.data()));
            publisher.publish("user.created", "John");
            
            assertThat(received).containsExactly("John");
        }
        
        @Test
        @DisplayName("Несколько подписчиков получают событие")
        void multipleSubscribersShouldReceiveEvent() {
            List<String> received1 = new ArrayList<>();
            List<String> received2 = new ArrayList<>();
            
            publisher.subscribe("event", e -> received1.add(e.data()));
            publisher.subscribe("event", e -> received2.add(e.data()));
            
            publisher.publish("event", "data");
            
            assertThat(received1).containsExactly("data");
            assertThat(received2).containsExactly("data");
        }
        
        @Test
        @DisplayName("Подписчик не получает события другого типа")
        void subscriberShouldNotReceiveOtherEvents() {
            List<String> received = new ArrayList<>();
            
            publisher.subscribe("type1", event -> received.add(event.data()));
            publisher.publish("type2", "data");
            
            assertThat(received).isEmpty();
        }
    }
    
    @Nested
    @DisplayName("Отписка")
    class Unsubscribe {
        
        @Test
        @DisplayName("Отписанный подписчик не получает события")
        void unsubscribedShouldNotReceiveEvents() {
            List<String> received = new ArrayList<>();
            
            Runnable unsubscribe = publisher.subscribe("event", e -> received.add(e.data()));
            publisher.publish("event", "before");
            
            unsubscribe.run();
            publisher.publish("event", "after");
            
            assertThat(received).containsExactly("before");
        }
    }
    
    @Nested
    @DisplayName("subscribeAll")
    class SubscribeAll {
        
        @Test
        @DisplayName("Получает все события")
        void shouldReceiveAllEvents() {
            List<String> received = new ArrayList<>();
            
            publisher.subscribeAll(event -> received.add(event.type() + ":" + event.data()));
            
            publisher.publish("type1", "a");
            publisher.publish("type2", "b");
            
            assertThat(received).containsExactly("type1:a", "type2:b");
        }
    }
    
    @Nested
    @DisplayName("subscriberCount")
    class SubscriberCount {
        
        @Test
        @DisplayName("Отслеживает количество подписчиков")
        void shouldTrackSubscriberCount() {
            assertThat(publisher.subscriberCount("event")).isEqualTo(0);
            
            Runnable unsub1 = publisher.subscribe("event", e -> {});
            assertThat(publisher.subscriberCount("event")).isEqualTo(1);
            
            publisher.subscribe("event", e -> {});
            assertThat(publisher.subscriberCount("event")).isEqualTo(2);
            
            unsub1.run();
            assertThat(publisher.subscriberCount("event")).isEqualTo(1);
        }
    }
}

