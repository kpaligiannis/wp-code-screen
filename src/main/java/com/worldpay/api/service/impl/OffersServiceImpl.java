package com.worldpay.api.service.impl;

import com.worldpay.api.cache.exception.ExpiredItemException;
import com.worldpay.api.cache.ExpiryCache;
import com.worldpay.api.cache.exception.ItemNotFoundException;
import com.worldpay.api.model.Offer;
import com.worldpay.api.service.OffersService;
import com.worldpay.api.service.exception.OfferExpiredException;
import com.worldpay.api.service.exception.OfferNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class OffersServiceImpl implements OffersService {
    private ExpiryCache<String, Offer> offersCache;

    @Autowired
    public OffersServiceImpl(@NonNull ExpiryCache<String, Offer> cache) {
        this.offersCache = cache;
    }

    public Offer getOffer(String Id) throws OfferNotFoundException, OfferExpiredException {
        try {
            return this.offersCache.get(Id);
        } catch (ExpiredItemException e) {
            throw new OfferExpiredException(e);
        } catch (ItemNotFoundException e) {
            throw new OfferNotFoundException(e);
        }
    }

    public Offer cancelOffer(String Id) throws OfferNotFoundException {
        try {
            return this.offersCache.evict(Id);
        } catch (ItemNotFoundException e) {
            throw new OfferNotFoundException(e);
        }
    }
}
