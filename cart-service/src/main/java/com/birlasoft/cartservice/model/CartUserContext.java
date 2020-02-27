package com.birlasoft.cartservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartUserContext {
    private Long userId;
    private Long cartId;
}
