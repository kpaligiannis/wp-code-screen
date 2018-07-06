package com.worldpay.api.web;

import com.worldpay.api.cache.ExpiryCache;
import com.worldpay.api.model.Offer;
import com.worldpay.api.service.MerchantService;
import com.worldpay.api.service.OffersService;
import com.worldpay.api.service.impl.MerchantServiceImpl;
import com.worldpay.api.service.impl.OffersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WPConfiguration {
    @Bean
    @Scope("singleton")
    public MerchantService merchantService(){
        return new MerchantServiceImpl(offerExpiryCache());
    }

    @Bean
    @Scope("singleton")
    public OffersService offersService() {
        return new OffersServiceImpl(offerExpiryCache());
    }

    @Bean
    @Scope("singleton")
    public ExpiryCache<String, Offer> offerExpiryCache(){
        return new ExpiryCache<>();
    }
}
