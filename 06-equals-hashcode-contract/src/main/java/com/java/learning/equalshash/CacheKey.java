package com.java.learning.equalshash;

import java.util.Arrays;

/**
 * Составной ключ для кэша.
 * 
 * Демонстрирует работу с массивами в equals/hashCode.
 * 
 * TODO: Реализуй equals() и hashCode()
 */
public final class CacheKey {
    
    private final String namespace;
    private final String key;
    private final String[] tags;
    
    public CacheKey(String namespace, String key, String... tags) {
        this.namespace = namespace;
        this.key = key;
        this.tags = tags != null ? tags.clone() : new String[0];
    }
    
    public String getNamespace() {
        return namespace;
    }
    
    public String getKey() {
        return key;
    }
    
    public String[] getTags() {
        return tags.clone(); // defensive copy
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: Реализуй equals
        // ВАЖНО: для массивов используй Arrays.equals()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public int hashCode() {
        // TODO: Реализуй hashCode
        // ВАЖНО: для массивов используй Arrays.hashCode()
        throw new UnsupportedOperationException("TODO: implement");
    }
    
    @Override
    public String toString() {
        return namespace + ":" + key + Arrays.toString(tags);
    }
}

