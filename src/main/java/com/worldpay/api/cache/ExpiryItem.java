package com.worldpay.api.cache;

public class ExpiryItem<V> {
    private V value;
    private long expiration;
    private long insertionTimestamp;

    public ExpiryItem(long expiration, long insertionTimestamp, V value) {
        this.expiration = expiration;
        this.insertionTimestamp = insertionTimestamp;
        this.value = value;
    }

    public V getValue() { return value; }

    public long getExpiration() {
        return expiration;
    }

    public long getInsertionTimestamp() {
        return insertionTimestamp;
    }

    public void setInsertionTimestamp(long insertionTimestamp) {
        this.insertionTimestamp = insertionTimestamp;
    }
}
