package com.birlasoft.cartservice.extservices;

import com.birlasoft.domain.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IProductClientWrapper {

    Product product(Long productId,String header);

    Iterable<Product> products();


}
