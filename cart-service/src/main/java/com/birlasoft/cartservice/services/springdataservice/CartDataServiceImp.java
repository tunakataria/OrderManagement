package com.birlasoft.cartservice.services.springdataservice;

import com.birlasoft.cartservice.repository.CartRepository;
import com.birlasoft.cartservice.repository.ProductDetailsRepository;
import com.birlasoft.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartDataServiceImp implements CartDataService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

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

    @Override
    public void deleteAllProducts(Long cartId) {
       Cart cart = cartRepository.findById(cartId).get();
            cart.getProductCountMap().entrySet().forEach(entry-> cart.getProductCountMap().remove(entry.getKey()));
      cartRepository.save(cart);
    }
}
