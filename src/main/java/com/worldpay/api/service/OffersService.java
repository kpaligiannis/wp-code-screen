package com.worldpay.api.service;

import com.worldpay.api.model.Offer;
import com.worldpay.api.service.exception.OfferExpiredException;
import com.worldpay.api.service.exception.OfferNotFoundException;

public interface OffersService {
    Offer getOffer(String Id) throws OfferNotFoundException, OfferExpiredException;
    Offer cancelOffer(String Id) throws OfferNotFoundException;
}
