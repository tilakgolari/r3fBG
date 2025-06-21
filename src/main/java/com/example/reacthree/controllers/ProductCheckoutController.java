package com.example.reacthree.controllers;


import com.example.reacthree.dto.ProductRequest;
import com.example.reacthree.dto.StripeResponse;
import com.example.reacthree.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCheckoutController {


    @Autowired
    private StripeService stripeService;

    public ProductCheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody List<ProductRequest> productRequestList) throws StripeException
    {

       Session session=stripeService.checkOutProducts(productRequestList);
        StripeResponse stripeResponse=new StripeResponse(session.getId(), session.getUrl());
        System.out.println(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }






}
