package com.worldpay.api.service.impl;

import com.worldpay.api.cache.ExpiryCache;
import com.worldpay.api.model.Offer;
import com.worldpay.api.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {
    private ExpiryCache<String, Offer> cache;

    @Autowired
    public MerchantServiceImpl(@NonNull ExpiryCache<String, Offer> cache) {
        this.cache = cache;
    }

    @Override
    public void submitOffer(Offer offer) {
        this.cache.push(offer.getId(), offer);
    }
}
