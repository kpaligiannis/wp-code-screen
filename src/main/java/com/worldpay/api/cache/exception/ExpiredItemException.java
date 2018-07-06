package com.worldpay.api.cache.exception;

public class ExpiredItemException extends Exception{
    public ExpiredItemException() {
        super("The item has expired in the cache.");
    }
}
