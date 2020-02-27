package com.birlasoft.cartservice.model;

import com.birlasoft.domain.Cart;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CartResponse {
    private Cart cart;
}
