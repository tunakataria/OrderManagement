package com.birlasoft.cartservice.api;

import com.birlasoft.domain.cart.Cart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cart-service/V1")
public interface CartLCApi {

  @GetMapping("/cart/{cartId}")
  Cart cart(@PathVariable  Long cartId);

  @DeleteMapping("/delete/{cartId}")
  void deleteProducts(@PathVariable Long cartId);
}
