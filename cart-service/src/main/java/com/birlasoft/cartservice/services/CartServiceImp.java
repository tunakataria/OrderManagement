package com.birlasoft.cartservice.services;

import com.birlasoft.cartservice.extservices.IProductServiceClient;
import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.ProductRequest;
import com.birlasoft.cartservice.services.springdataservice.CartDataService;
import com.birlasoft.domain.Cart;
import com.birlasoft.domain.Product;
import com.birlasoft.domain.ProductCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImp implements ICartService {

    @Autowired
    private CartDataService cartDataService;

    @Autowired
    private IProductServiceClient iProductServiceClient;

    @Override
    public CartResponse processInternal(ProductRequest request) {
        Long userID = cartUserContext.getUserId();
        Cart cart = checkUserHasACart(userID);
        updateContext(cart.getId());
        if(request.getTYPE()== ProductRequest.UpdateType.ADDITION){
           cart = addProduct(request.getProductId());
        }else{
           cart = removeProduct(request.getProductId());
        }
        return CartResponse.builder().cart(cart).build();
    }

    private Cart checkUserHasACart(Long userID) {
        return cartDataService.findCartAssociatedToUser(userID);
    }

    public Cart addProduct(Long productId) {
        Cart cart = cartDataService.findById(cartUserContext.getCartId()).get();
        Product product = iProductServiceClient.product(productId);
        addProduct(product);
        cartDataService.save(cart);
        return cart;
    }


    public Cart removeProduct(Long productId) {
        Cart cart = cartDataService.findById(cartUserContext.getCartId()).get();
        Product product = iProductServiceClient.product(productId);
        cartDataService.save(cart);
        return cart;
    }

    private void addProduct(Product product){
        Cart cart = cartDataService.findById(cartUserContext.getCartId()).get();
        Map<Product, ProductCounter> productCountMap = cart.getProductCountMap();
        if(productCountMap==null){
            productCountMap = new HashMap<>();
            ProductCounter productCounter = new ProductCounter();
            productCounter.setProduct(product);
            productCounter.setCountOfAProduct(1);
            productCountMap.put(product, productCounter);
            cart.setProductCountMap(productCountMap);
            cartDataService.save(cart);
        }else {
            if (productCountMap.get(product) == null) {
                ProductCounter counter = new ProductCounter();
                counter.setProduct(product);
                counter.setCountOfAProduct(1);
                productCountMap.put(product, counter);
            } else {
                ProductCounter productCount = productCountMap.get(product);
                int count = productCount.getCountOfAProduct() + 1;
                productCount.setCountOfAProduct(count);
                productCountMap.put(product, productCount);
            }
        }
    }
}
