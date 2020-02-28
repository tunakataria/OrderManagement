package com.birlasoft.cartservice.controller;

import com.birlasoft.cartservice.api.CartServiceApi;
import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.ProductRequest;
import com.birlasoft.cartservice.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController implements CartServiceApi {

    @Autowired
    ICartService cartService;

    @Override
    public CartResponse addProduct(ProductRequest productRequest) {
        return cartService.process(productRequest);
    }

    @Override
    public CartResponse removeProduct(ProductRequest productRequest) {
        return cartService.process(productRequest);
    }
}
