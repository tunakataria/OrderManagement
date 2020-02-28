package com.birlasoft.cartservice.command;

import com.birlasoft.domain.Cart;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Cart2CartDto {
    public static CartDto cart2CartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setUserId(cart.getUserId());
        cartDto.setListOfProductDetails(cart.getProductCountMap().entrySet().stream().map(entry ->
                entry.getValue()).map(productDetails -> ProductDetails2ProductDetailsDto.productDetails2ProductDetailsDto(productDetails)).collect(Collectors.toList()));
        cartDto.setTotalProducts(cartDto.getListOfProductDetails().stream().map(productDetailsDto -> productDetailsDto.getCountOfAProduct()).count());
        return cartDto;
    }
}
