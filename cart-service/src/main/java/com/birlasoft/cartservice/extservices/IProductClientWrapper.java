package com.birlasoft.cartservice.extservices;

import com.birlasoft.domain.Product;

public interface IProductClientWrapper {

    Product product(Long productId,String header);

    Iterable<Product> products();


}
