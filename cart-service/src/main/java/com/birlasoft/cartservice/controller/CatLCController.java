package com.birlasoft.cartservice.controller;

import com.birlasoft.cartservice.api.CartLCApi;
import com.birlasoft.cartservice.services.springdataservice.CartDataService;
import com.birlasoft.domain.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatLCController implements CartLCApi {

    @Autowired
    private CartDataService cartDataService;


    @Override
    public Cart cart(Long cartId) {
        return cartDataService.findById(cartId).get();
    }

    @Override
    public void deleteProducts(Long cartId) {
        cartDataService.deleteAllProducts(cartId);
    }
}
