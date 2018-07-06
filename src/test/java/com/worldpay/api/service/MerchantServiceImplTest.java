package com.worldpay.api.service;

import com.worldpay.api.cache.ExpiryCache;
import com.worldpay.api.cache.exception.ExpiredItemException;
import com.worldpay.api.cache.exception.ItemNotFoundException;
import com.worldpay.api.model.Offer;
import com.worldpay.api.service.impl.MerchantServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.worldpay.api.utils.TestObjects.TEST_OFFER;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MerchantServiceImplTest {
    private static ExpiryCache<String, Offer> cache = new ExpiryCache<>();

    @TestConfiguration
    public static class MerchantServiceImplTestConfiguration {
        @Bean
        public MerchantServiceImpl merchantService() {
            return new MerchantServiceImpl(cache);
        }
    }

    @Autowired
    private MerchantService merchantService;

    @Test
    public void createsAnOfferInCache() throws ExpiredItemException, ItemNotFoundException {
        merchantService.submitOffer(TEST_OFFER);
        Offer offer = cache.get(TEST_OFFER.getId());
        assertEquals(offer.getId(), TEST_OFFER.getId());
    }
}