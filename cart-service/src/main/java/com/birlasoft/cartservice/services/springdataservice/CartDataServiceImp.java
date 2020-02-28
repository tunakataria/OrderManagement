package com.birlasoft.cartservice.services.springdataservice;

import com.birlasoft.cartservice.repository.CartRepository;
import com.birlasoft.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDataServiceImp implements CartDataService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Cart> findById(Long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartAssociatedToUser(Long userId) {
        Optional<Cart> cart = ((List<Cart>) cartRepository.findAll()).stream().filter($ -> $.getUserId().longValue() == userId).findFirst();
        return cart.orElseGet(() -> {
            Cart emptyCart = new Cart();
            emptyCart.setUserId(userId);
            save(emptyCart);
            return emptyCart;
        });
    }
}
