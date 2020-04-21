package com.birlasoft.productservice.services;

import com.birlasoft.productservice.exceptions.ProcessingException;
import com.birlasoft.productservice.repository.ProductRepository;
import com.birlasoft.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProcessingException("Product not available"));
    }

    public Iterable<Product> findProducts() {
        Iterable<Product> products = productRepository.findAll();
        return products;
    }
}
