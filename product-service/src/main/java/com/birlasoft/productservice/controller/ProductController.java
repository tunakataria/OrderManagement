package com.birlasoft.productservice.controller;

import com.birlasoft.productservice.api.IProductService;
import com.birlasoft.productservice.services.FindProductService;
import com.birlasoft.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements IProductService {

    @Autowired
    private FindProductService findProductService;

    @Override
    public Product product(Long productId) {
        return findProductService.findProduct(productId);
    }

    @Override
    public Iterable<Product> products() {
        return findProductService.findProducts();
    }
}
