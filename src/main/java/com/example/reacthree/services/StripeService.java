package com.example.reacthree.services;


import com.example.reacthree.dto.ProductRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;


    @PostConstruct
    public void init(){
    Stripe.apiKey=stripeSecretKey;

}


public Session getSession(String sessionId) throws StripeException
{
    Map<String,Object> params=new HashMap<>();
    params.put("expand[]","line_items");

    return Session.retrieve(sessionId,params,null);



}





public Session checkOutProducts(List<ProductRequest> productRequestList) {


    List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

    for (ProductRequest productRequest : productRequestList) {

        sessionItemList.add(createSessionLineItem(productRequest));

    }




    SessionCreateParams params = SessionCreateParams.builder()

            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setCancelUrl("http://localhost:5173/cancel")
            .setSuccessUrl("http://localhost:5173/success")
            .addAllLineItem(sessionItemList)
            .build();


    try {
        return Session.create(params);
    } catch (StripeException e) {
        throw new RuntimeException(e);
    }


}

    private SessionCreateParams.LineItem createSessionLineItem(ProductRequest productRequest) {

            return SessionCreateParams.LineItem.builder()
                    .setPriceData(createPriceData(productRequest))
                    .setQuantity(1L)
                    .build();

    }
    private SessionCreateParams.LineItem.PriceData createPriceData(ProductRequest productRequest) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("INR")
                .setUnitAmount((long)(productRequest.getPrice()*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(productRequest.getName())
                                .build()
                ).build();
    }
}

