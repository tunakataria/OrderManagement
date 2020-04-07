package com.birlasoft.cartservice.extservices;

import com.birlasoft.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.FallbackDataService;

@Component
public class ProductClientWrapper implements IProductClientWrapper {

    @Autowired
    private IProductServiceClient productServiceClient;



    @Override
    @HystrixCommand(fallbackMethod = "getSavedProduct")
    public Product product(Long productId, String header) {
        return productServiceClient.product(productId,header);
    }

    @Override
    public Iterable<Product> products() {
        return productServiceClient.products();
    }

    public Product getSavedProduct(Long productId, String authHeader) {
        return FallbackDataService.getProduct(productId);
    }
}
