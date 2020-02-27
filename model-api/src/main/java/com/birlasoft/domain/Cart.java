package com.birlasoft.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_order_mapping",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "counter_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "product_Id")
    private Map<Long, ProductCounter> productCountMap;
    private Long userId;

  /*  public void addProduct(Product product) {
        if (productCountMap.get(product) == null) {
            ProductCounter counter = new ProductCounter();
            counter.setProduct(product);
            counter.setCountOfAProduct(1);
            productCountMap.put(product, counter);
        } else {
            ProductCounter productCount = productCountMap.get(product);
            int count = productCount.getCountOfAProduct() + 1;
            productCount.setCountOfAProduct(count);
            productCountMap.put(product, productCount);
        }
    }

    public void removeProduct(Product product) {
        if (productCountMap.get(product) == null || productCountMap.get(product).getCountOfAProduct() == 1) {
            productCountMap.remove(product);
        } else {

            ProductCounter counter = productCountMap.get(product);
            int productCount = counter.getCountOfAProduct() - 1;
            counter.setCountOfAProduct(productCount);

            productCountMap.put(product, counter);
        }
    }*/
}
