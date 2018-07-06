package com.worldpay.api.cache;

import com.worldpay.api.cache.exception.ExpiredItemException;
import com.worldpay.api.cache.exception.ItemNotFoundException;
import com.worldpay.api.model.Offer;
import org.junit.Test;

import static com.worldpay.api.utils.TestObjects.SHORT_LIVED_TEST_OFFER;
import static com.worldpay.api.utils.TestObjects.TEST_OFFER;
import static org.junit.Assert.*;

public class ExpiryCacheTest {
    @Test
    public void addAndRetrieveItem() throws ExpiredItemException, ItemNotFoundException {
        ExpiryCache<String, Offer> expiryCache = new ExpiryCache<>();
        expiryCache.push(TEST_OFFER.getId(), TEST_OFFER);
        Offer offer = expiryCache.get(TEST_OFFER.getId());
        assertNotEquals(offer, null);
        assertEquals(offer.getId(), TEST_OFFER.getId());
    }

    @Test(expected = ExpiredItemException.class)
    public void itemExpiresAndThrowsException() throws ExpiredItemException, InterruptedException, ItemNotFoundException {
        ExpiryCache<String, Offer> expiryCache = new ExpiryCache<>();
        expiryCache.push(SHORT_LIVED_TEST_OFFER.getId(), SHORT_LIVED_TEST_OFFER);
        Thread.sleep(200);
        expiryCache.get(SHORT_LIVED_TEST_OFFER.getId());
    }

    @Test(expected = ItemNotFoundException.class)
    public void evictsItem() throws ExpiredItemException, ItemNotFoundException {
        ExpiryCache<String, Offer> expiryCache = new ExpiryCache<>();
        expiryCache.push(TEST_OFFER.getId(), TEST_OFFER);
        expiryCache.evict(TEST_OFFER.getId());
        expiryCache.get(TEST_OFFER.getId());
    }

}