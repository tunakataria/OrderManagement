package com.birlasoft.cartservice.extservices;

import com.birlasoft.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service", url = "http://localhost:9080")
@RequestMapping("/product-service/V1")
public interface IProductServiceClient {

    @GetMapping("/product/{productId}")
    Product product(@PathVariable Long productId);

    @GetMapping("/products")
    Iterable<Product> products();
}
