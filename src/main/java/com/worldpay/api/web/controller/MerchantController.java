package com.worldpay.api.web.controller;

import com.worldpay.api.model.Offer;
import com.worldpay.api.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @PostMapping("/merchant/{merchantid}/offer")
    @ResponseBody
    public void createOffer(@PathVariable("merchantid") String merchantid,
                            @RequestBody Offer offer) {
        this.merchantService.submitOffer(offer);
    }
}
