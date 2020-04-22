package com.birlasoft.orderservice.extapi;

import com.birlasoft.domain.cart.Cart;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "zuul-proxy-gateway-server")
@RibbonClient(name="zuul-proxy-gateway-server")
@RequestMapping("/cart-service/cart-service/V1")
public interface CartApiProxy {

    @GetMapping("/cart/{cartId}")
    Cart cart(@PathVariable  Long cartId, @RequestHeader(HttpHeaders.AUTHORIZATION) String header);

    @DeleteMapping("/delete/{cartId}")
    void deleteProducts(@PathVariable Long cartId, @RequestHeader(HttpHeaders.AUTHORIZATION) String header);
}
