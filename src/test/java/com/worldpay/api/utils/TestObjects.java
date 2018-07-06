package com.worldpay.api.utils;

import com.worldpay.api.model.Offer;

public interface TestObjects {
    Offer TEST_OFFER = new Offer("1", "1", 100000000,  100,"GBP", "This is a nice description");
    Offer SHORT_LIVED_TEST_OFFER = new Offer("1", "1", 100, 100,"GBP", "This is a nice description");
}
