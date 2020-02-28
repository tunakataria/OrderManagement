package com.birlasoft.cartservice.services;

import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.CartUserContext;
import com.birlasoft.cartservice.model.ProductRequest;

public interface ICartService {

    CartUserContext cartUserContext = new CartUserContext();

    default CartUserContext initContext(ProductRequest productRequest) {
        cartUserContext.setUserId(productRequest.getUserId());
        return cartUserContext;
    }

    default CartResponse process(ProductRequest productRequest) {
        initContext(productRequest);
        return processInternal(productRequest);
    }

    default CartUserContext updateContext(Long cartId) {
        cartUserContext.setCartId(cartId);
        return cartUserContext;
    }

    CartResponse processInternal(ProductRequest request);

}
