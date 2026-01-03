package com.java.learning.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ring Buffer")
class RingBufferTest {

    @Nested
    @DisplayName("Базовые операции")
    class BasicOperations {
        
        private RingBuffer<Integer> buffer;
        
        @BeforeEach
        void setUp() {
            buffer = new RingBuffer<>(3);
        }
        
        @Test
        @DisplayName("offer добавляет элементы")
        void shouldOfferElements() {
            assertThat(buffer.offer(1)).isTrue();
            assertThat(buffer.offer(2)).isTrue();
            assertThat(buffer.size()).isEqualTo(2);
        }
        
        @Test
        @DisplayName("poll извлекает в порядке FIFO")
        void shouldPollInFIFOOrder() {
            buffer.offer(1);
            buffer.offer(2);
            buffer.offer(3);
            
            assertThat(buffer.poll()).isEqualTo(1);
            assertThat(buffer.poll()).isEqualTo(2);
            assertThat(buffer.poll()).isEqualTo(3);
            assertThat(buffer.poll()).isNull();
        }
        
        @Test
        @DisplayName("peek не удаляет элемент")
        void shouldPeekWithoutRemoving() {
            buffer.offer(1);
            buffer.offer(2);
            
            assertThat(buffer.peek()).isEqualTo(1);
            assertThat(buffer.peek()).isEqualTo(1);
            assertThat(buffer.size()).isEqualTo(2);
        }
    }
    
    @Nested
    @DisplayName("Переполнение буфера")
    class Overflow {
        
        @Test
        @DisplayName("offer возвращает false при переполнении")
        void shouldRejectWhenFull() {
            RingBuffer<Integer> buffer = new RingBuffer<>(2);
            
            assertThat(buffer.offer(1)).isTrue();
            assertThat(buffer.offer(2)).isTrue();
            assertThat(buffer.offer(3)).isFalse();
            assertThat(buffer.size()).isEqualTo(2);
        }
        
        @Test
        @DisplayName("forceOffer перезаписывает старые элементы")
        void shouldOverwriteOnForce() {
            RingBuffer<Integer> buffer = new RingBuffer<>(2);
            
            buffer.offer(1);
            buffer.offer(2);
            
            Integer overwritten = buffer.forceOffer(3);
            
            assertThat(overwritten).isEqualTo(1);
            assertThat(buffer.poll()).isEqualTo(2);
            assertThat(buffer.poll()).isEqualTo(3);
        }
        
        @Test
        @DisplayName("forceOffer в непустой буфер без переполнения")
        void forceOfferWithoutOverflow() {
            RingBuffer<Integer> buffer = new RingBuffer<>(3);
            
            buffer.offer(1);
            Integer overwritten = buffer.forceOffer(2);
            
            assertThat(overwritten).isNull();
            assertThat(buffer.size()).isEqualTo(2);
        }
    }
    
    @Nested
    @DisplayName("Wrap-around (кольцевое поведение)")
    class WrapAround {
        
        @Test
        @DisplayName("Корректно работает при многократном wrap-around")
        void shouldHandleMultipleWrapArounds() {
            RingBuffer<Integer> buffer = new RingBuffer<>(3);
            
            // Заполняем и освобождаем несколько раз
            for (int cycle = 0; cycle < 5; cycle++) {
                buffer.offer(cycle * 10 + 1);
                buffer.offer(cycle * 10 + 2);
                buffer.offer(cycle * 10 + 3);
                
                assertThat(buffer.poll()).isEqualTo(cycle * 10 + 1);
                assertThat(buffer.poll()).isEqualTo(cycle * 10 + 2);
                assertThat(buffer.poll()).isEqualTo(cycle * 10 + 3);
            }
        }
    }
    
    @Nested
    @DisplayName("Итерация")
    class Iteration {
        
        @Test
        @DisplayName("Итератор обходит элементы в порядке FIFO")
        void shouldIterateInFIFOOrder() {
            RingBuffer<Integer> buffer = new RingBuffer<>(5);
            buffer.offer(1);
            buffer.offer(2);
            buffer.offer(3);
            
            List<Integer> result = new ArrayList<>();
            for (Integer item : buffer) {
                result.add(item);
            }
            
            assertThat(result).containsExactly(1, 2, 3);
        }
        
        @Test
        @DisplayName("Итерация после wrap-around")
        void shouldIterateAfterWrapAround() {
            RingBuffer<Integer> buffer = new RingBuffer<>(3);
            
            buffer.offer(1);
            buffer.offer(2);
            buffer.offer(3);
            buffer.poll(); // Удаляем 1
            buffer.poll(); // Удаляем 2
            buffer.offer(4);
            buffer.offer(5);
            
            List<Integer> result = new ArrayList<>();
            for (Integer item : buffer) {
                result.add(item);
            }
            
            assertThat(result).containsExactly(3, 4, 5);
        }
    }
}

