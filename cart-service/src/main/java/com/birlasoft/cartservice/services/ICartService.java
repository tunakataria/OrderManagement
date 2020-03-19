package com.birlasoft.cartservice.services;

import com.birlasoft.cartservice.context.UserServiceReqContext;
import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.CartUserContext;
import com.birlasoft.cartservice.model.ProductRequest;

public interface ICartService {



     UserServiceReqContext initContext(ProductRequest productRequest);

    default CartResponse process(ProductRequest productRequest) {
        initContext(productRequest);
        return processInternal(productRequest);
    }


    CartResponse processInternal(ProductRequest request);

}
