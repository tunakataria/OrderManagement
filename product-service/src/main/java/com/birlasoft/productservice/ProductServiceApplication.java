package com.birlasoft.productservice;

import com.birlasoft.productservice.bootstrap.ProductsDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("com.birlasoft.domain.product")
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public ProductsDataLoader productsDataLoader() {
        return new ProductsDataLoader();
    }
}
