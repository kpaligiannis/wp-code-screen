package com.worldpay.api.cache.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() { super("Item not found in cache."); }
}
