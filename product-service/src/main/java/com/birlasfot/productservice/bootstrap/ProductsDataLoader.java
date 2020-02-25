package com.birlasfot.productservice.bootstrap;

import com.birlasfot.productservice.repository.ProductRepository;
import com.birlasoft.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class ProductsDataLoader implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        //TODO add product data

        //Product one
        Product productOne = new Product();
        productOne.setCategory("Cosmetic");
        productOne.setProductName("HeadAndShoulder");

        //Product two
        Product productTwo = new Product();
        productTwo.setCategory("Hygiene");
        productTwo.setProductName("Harpic");

        //Product two
        Product productThree = new Product();
        productThree.setCategory("Grocery");
        productThree.setProductName("Oatmeal");

        productRepository.save(productOne);
        productRepository.save(productTwo);
        productRepository.save(productThree);
        LOG.info("All the products were saved in H2-DB");

    }
}
