package com.birlasoft.cartservice.services.springdataservice;

import com.birlasoft.domain.Cart;

import java.util.Optional;

public interface CartDataService {

    Optional<Cart> findById(Long var1);

    Cart save(Cart cart);

    Cart findCartAssociatedToUser(Long userId);

     void deleteAllProducts(Long cartId);
}
