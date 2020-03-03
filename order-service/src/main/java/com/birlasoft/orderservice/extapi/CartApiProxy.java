package com.birlasoft.orderservice.extapi;

import com.birlasoft.domain.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cart-service", url = "http://localhost:9060")
@RequestMapping("/cart-service/V1")
public interface CartApiProxy {

    @GetMapping("/cart/{cartId}")
    Cart cart(@PathVariable  Long cartId);

    @DeleteMapping("/delete/{cartId}")
    void deleteProducts(@PathVariable Long cartId);
}
