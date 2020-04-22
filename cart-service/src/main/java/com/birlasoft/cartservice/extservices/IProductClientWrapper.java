package com.birlasoft.cartservice.extservices;

import com.birlasoft.domain.product.Product;

public interface IProductClientWrapper {

    Product product(Long productId,String header);

    Iterable<Product> products();


}
