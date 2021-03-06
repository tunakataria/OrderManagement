package com.birlasoft.cartservice.api;

import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.ProductRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cart-service/V1")
public interface CartServiceApi {

    @PostMapping("/addProduct")
    CartResponse addProduct(@RequestBody ProductRequest productRequest);

    @PostMapping("/removeProduct")
    CartResponse removeProduct(@RequestBody ProductRequest productRequest);
}
