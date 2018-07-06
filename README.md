# WorldPay Code Screen
This is a crude RESTful API for users to create, retrieve and cancel Offers.
It uses in memory storage with a TTL on the items stored.

There are 3 endpoints at the moment:

* POST /merchant/{merchantid}/offer , for merchants to create a new Offer
  It accepts an Offer object as below.

  ```json
   {
     "offer" : {
        "id" : "123",
        "merchant_id" : "123",
        "expiration" : "1000",  //in milliseconds
        "price" : "100.0", //double
        "currency" : "GBP",
        "description" : "A user friendly description of the offer"
     }
   }
  ```

* GET  /offers/{offerid} , to retrieve an offer with the specified id. It will return a 404 if the offer is not found or
                            has expired
* PUT  /offer-cancellations/{offerid} , to cancel an offer

## Assumptions
The API implies there is a Merchant entity but for simplicity it's not stored in memory and hence completely ignored. In
the real world this would be stored and cross referenced every time a merchant tries to create a new offer.

The Offer id is created by the user and it is assumed correct. There are no checks on the id and if an Offer reuses an id
it will simply update the current value in the system.

When an offer expires or gets cancelled, it is completely discarded and cannot be retrieved anymore. Future requests with
the id of a discarded offer will return 404.

## Built With

Maven, Springboot and JAVA 8

