package com.worldpay.api.service;

import com.worldpay.api.model.Offer;

public interface MerchantService {
    void submitOffer(Offer offer);
}
