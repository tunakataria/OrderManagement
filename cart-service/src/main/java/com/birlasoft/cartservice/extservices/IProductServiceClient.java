package com.birlasoft.cartservice.extservices;

import com.birlasoft.cartservice.config.RestClientConfig;
import com.birlasoft.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import util.FallbackDataService;


@RibbonClient(name = "zuul-proxy-gateway-server")
@FeignClient(name = "zuul-proxy-gateway-server", configuration = RestClientConfig.class)
@RequestMapping("/product-service/product-service/V1")
public interface IProductServiceClient {


    @GetMapping("/product/{productId}")
    Product product(@PathVariable Long productId, @RequestHeader(HttpHeaders.AUTHORIZATION) String header);

    @GetMapping("/products")
    Iterable<Product> products();



}
