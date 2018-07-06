package com.worldpay.api.web.controller;

import com.worldpay.api.model.Offer;
import com.worldpay.api.service.OffersService;
import com.worldpay.api.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {
    @Autowired
    OffersService offerService;

    @GetMapping("/offer/{offerid}")
    @ResponseBody
    public Offer getOffer(@PathVariable("offerid") String offerId) {
        try {
            return offerService.getOffer(offerId);
        } catch (Exception e){
           throw new NotFoundException("The offer wasn't found");
        }
    }

}
