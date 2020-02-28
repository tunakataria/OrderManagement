package com.birlasoft.cartservice.command;

import com.birlasoft.domain.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public class ProductDetails2ProductDetailsDto {
    public static ProductDetailsDto productDetails2ProductDetailsDto(ProductDetails productDetails) {
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();
        productDetailsDto.setProductName(productDetails.getProductName());
        productDetailsDto.setProductPrice(productDetails.getProductPrice());
        productDetailsDto.setProductRef(productDetails.getProductRef());
        productDetailsDto.setCountOfAProduct(productDetails.getCountOfAProduct());
        return productDetailsDto;

    }
}
