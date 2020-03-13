package com.birlasoft.productservice.api;

import com.birlasoft.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product-service/V1")
public interface IProductService {

    @GetMapping("/product/{productId}")
    Product product(@PathVariable Long productId);

    @GetMapping("/products")
    Iterable<Product> products();
}
