package com.birlasoft.orderservice.extapi;

import com.birlasoft.domain.Cart;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zuul-proxy-gateway-server")
@RibbonClient(name="zuul-proxy-gateway-server")
@RequestMapping("/cart-service/cart-service/V1")
public interface CartApiProxy {

    @GetMapping("/cart/{cartId}")
    Cart cart(@PathVariable  Long cartId);

    @DeleteMapping("/delete/{cartId}")
    void deleteProducts(@PathVariable Long cartId);
}
