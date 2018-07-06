package com.worldpay.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldpay.api.cache.ExpiryItem;
import com.worldpay.api.cache.ExpirationAwareCacheEntry;

public class Offer implements ExpirationAwareCacheEntry {
    private String Id;
    private String merchantId;
    private double price;
    private String currency;
    private long expiration;
    private String description;

    @JsonCreator
    public Offer(@JsonProperty("id") String Id,
                 @JsonProperty("merchant_id") String merchantId,
                 @JsonProperty("expiration") long expiration,
                 @JsonProperty("price") double price,
                 @JsonProperty("currency") String currency,
                 @JsonProperty("description") String description) {
        this.Id = Id;
        this.merchantId = merchantId;
        this.expiration = expiration;
        this.currency = currency;
        this.description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
