package com.birlasoft.cartservice.command;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductDetailsDto {
    private Long productRef;
    private String productName;
    private String productPrice;
    private String category;
    private int countOfAProduct;
}
