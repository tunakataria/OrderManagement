package com.birlasoft.cartservice.command;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CartDto {
    private Long cartId;
    private Long userId;
    private List<ProductDetailsDto> listOfProductDetails;
    private Long totalProducts;
}
