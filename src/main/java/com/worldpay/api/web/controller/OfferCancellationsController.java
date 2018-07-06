package com.worldpay.api.web.controller;

import com.worldpay.api.model.Offer;
import com.worldpay.api.service.OffersService;
import com.worldpay.api.service.exception.OfferNotFoundException;
import com.worldpay.api.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferCancellationsController {
    @Autowired
    private OffersService offerService;
    private static String MESSAGE = "The offer wasn't found";

    @PutMapping("/offer-cancellation/{offerid}")
    @ResponseBody
    public boolean createOfferCancelation(@PathVariable("offerid") String offerId) {
        try {
            Offer offer = this.offerService.cancelOffer(offerId);
            if(offer == null) {
                throw new NotFoundException(MESSAGE);
            }
        } catch (OfferNotFoundException e) {
            throw new NotFoundException(MESSAGE);
        }

        return true;
    }
}
