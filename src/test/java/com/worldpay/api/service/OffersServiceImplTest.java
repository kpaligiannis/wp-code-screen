package com.worldpay.api.service;

import com.worldpay.api.cache.ExpiryCache;
import com.worldpay.api.model.Offer;
import com.worldpay.api.service.exception.OfferExpiredException;
import com.worldpay.api.service.exception.OfferNotFoundException;
import com.worldpay.api.service.impl.MerchantServiceImpl;
import com.worldpay.api.service.impl.OffersServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.worldpay.api.utils.TestObjects.TEST_OFFER;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class OffersServiceImplTest {
    private static ExpiryCache<String, Offer> cache = new ExpiryCache<>();

    @TestConfiguration
    static class OffersServiceImplTestConfiguration {
        @Bean
        public OffersServiceImpl offersService() {
            return new OffersServiceImpl(cache);
        }

        @Bean
        public MerchantServiceImpl merchantService() {
            return new MerchantServiceImpl(cache);
        }
    }

    @Autowired
    private OffersService offersService;
    @Autowired
    private MerchantService merchantService;

    @Test
    public void getsOffer() throws OfferExpiredException, OfferNotFoundException {
        merchantService.submitOffer(TEST_OFFER);
        Offer offer = offersService.getOffer(TEST_OFFER.getId());
        assertEquals(offer.getId(), TEST_OFFER.getId());
    }

    @Test
    public void cancelsOfferSuccessfully() throws OfferNotFoundException {
        merchantService.submitOffer(TEST_OFFER);
        Offer offer = offersService.cancelOffer(TEST_OFFER.getId());
        assertEquals(offer.getId(), TEST_OFFER.getId());
    }

}