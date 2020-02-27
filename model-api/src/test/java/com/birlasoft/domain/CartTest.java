package com.birlasoft.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest {

    private Cart objectUnderTest;

    @BeforeEach
    void setUp() {
        objectUnderTest = new Cart();
    }

  /*  @Test
    void product_count_map_should_update_when_a_product_is_added_for_the_firstTime() {
        Product product = getProduct(1L);
        objectUnderTest.addProduct(product);
        int count = objectUnderTest.getProductCountMap().get(product).getCountOfAProduct();
        Assertions.assertThat(count).isEqualTo(1);
        objectUnderTest.addProduct(product);
        count = objectUnderTest.getProductCountMap().get(product).getCountOfAProduct();
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    void product_should_be_removed_and_the_counter_should_be_updated() {
        Product product = getProduct(1L);
        objectUnderTest.addProduct(product);
        objectUnderTest.addProduct(product);
        objectUnderTest.removeProduct(product);
        int count = objectUnderTest.getProductCountMap().get(product).getCountOfAProduct();
        Assertions.assertThat(count).isEqualTo(1);
        objectUnderTest.removeProduct(product);
        Assertions.assertThat(objectUnderTest.getProductCountMap().get(product)==null);
    }

    private Product getProduct(Long id) {
        Product product = new Product();
        product.setProductName("Shampoo");
        product.setId(id);
        return product;
    }
*/
    @AfterEach
    void tearDown() {
    }
}