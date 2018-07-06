package com.worldpay.api.cache;

import com.worldpay.api.cache.exception.ExpiredItemException;
import com.worldpay.api.cache.exception.ItemNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExpiryCache<K,V extends ExpirationAwareCacheEntry> {
    private final Map<K, ExpiryItem<V>> cache;

    public ExpiryCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void push(K key, V value) {
        ExpiryItem<V> entry = new ExpiryItem<>(value.getExpiration(), System.currentTimeMillis(), value);
        cache.put(key, entry);
    }

    public V get(K key) throws ExpiredItemException, ItemNotFoundException {
        ExpiryItem<V> entry = cache.get(key);
        if(entry == null ) {
            throw new ItemNotFoundException();
        }
        if(System.currentTimeMillis() - entry.getInsertionTimestamp() > entry.getExpiration()) {
            cache.remove(key);
            throw new ExpiredItemException();
        }
        return entry.getValue();
    }

    public V evict(K key) throws ItemNotFoundException {
        ExpiryItem<V> entry = cache.get(key);
        if(entry == null) {
            throw new ItemNotFoundException();
        }
        cache.remove(key);

        return entry.getValue();
    }
}
