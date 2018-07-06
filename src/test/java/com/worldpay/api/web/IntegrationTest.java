package com.worldpay.api.web;

import com.worldpay.api.model.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.worldpay.api.utils.TestObjects.TEST_OFFER;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createsRetrievesAndCancelsOffer() throws Exception {
        //Create
        ResponseEntity<Offer> offerResponseEntity =
                restTemplate.postForEntity("/merchant/1/offer", TEST_OFFER, Offer.class);
        assertEquals(HttpStatus.OK, offerResponseEntity.getStatusCode());

        //Retrieve
        offerResponseEntity = restTemplate.getForEntity("/offer/"+TEST_OFFER.getId(), Offer.class);
        assertEquals(offerResponseEntity.getBody().getId(), TEST_OFFER.getId());

        //Cancel and try to retrieve again
        restTemplate.put("/offer-cancellation/"+TEST_OFFER.getId(), null);
        offerResponseEntity = restTemplate.getForEntity("/offer/"+TEST_OFFER.getId(), Offer.class);
        assertEquals(offerResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}