package com.birlasoft.cartservice.services;

import com.birlasoft.cartservice.command.Cart2CartDto;
import com.birlasoft.cartservice.context.UserServiceReqContext;
import com.birlasoft.cartservice.extservices.IProductServiceClient;
import com.birlasoft.cartservice.model.CartResponse;
import com.birlasoft.cartservice.model.ProductRequest;
import com.birlasoft.cartservice.services.springdataservice.CartDataService;
import com.birlasoft.domain.Cart;
import com.birlasoft.domain.Product;
import com.birlasoft.domain.ProductDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CartServiceImp implements ICartService {


    @Autowired
    private CartDataService cartDataService;

    @Autowired
    private IProductServiceClient iProductServiceClient;

    @Autowired
    private Cart2CartDto cart2CartDto;

    @Autowired
    UserServiceReqContext userServiceReqContext;

    @Override
    public UserServiceReqContext initContext(ProductRequest productRequest) {
         userServiceReqContext.setUserId(productRequest.getUserId());
         return userServiceReqContext;
    }

    @Override
    public CartResponse processInternal(ProductRequest request) {
        Long userID = request.getUserId();
        Cart cart = checkUserHasACart(userID);
        userServiceReqContext.setCartId(cart.getId());
        if (request.getTYPE() == ProductRequest.UpdateType.ADDITION) {
            cart = addProduct(request.getProductId());
        } else {
            cart = removeProduct(request.getProductId());
        }
        return CartResponse.builder().cart(cart2CartDto.cart2CartDto(cart)).build();
    }

    private Cart checkUserHasACart(Long userID) {
        return cartDataService.findCartAssociatedToUser(userID);
    }

    public Cart addProduct(Long productId) {
        Cart cart = cartDataService.findById(userServiceReqContext.getCartId()).get();
        Product product = iProductServiceClient.product(productId);
        addProduct(product);
        cartDataService.save(cart);
        return cart;
    }


    public Cart removeProduct(Long productId) {
        Cart cart = cartDataService.findById(userServiceReqContext.getCartId()).get();
        ProductDetails productDetails = cart.getProductCountMap().get(productId);
        if (productDetails.getCountOfAProduct() == 1) {
            cart.getProductCountMap().remove(productId);
            cartDataService.save(cart);
        } else {
            int initialCount = productDetails.getCountOfAProduct();
            productDetails.setCountOfAProduct(initialCount - 1);
        }
        cartDataService.save(cart);
        return cart;
    }

    private void addProduct(Product product) {
        Cart cart = cartDataService.findById(userServiceReqContext.getCartId()).get();
        Map<Long, ProductDetails> mapProductDetails = cart.getProductCountMap();
        ProductDetails productDetails;
        if (mapProductDetails == null) {
            productDetails = product2ProductDetails(product);
            mapProductDetails = new HashMap<>();
            productDetails.setCountOfAProduct(1);
            mapProductDetails.put(product.getId(), productDetails);
            cart.setProductCountMap(mapProductDetails);
            cartDataService.save(cart);
        } else {
            if (mapProductDetails.get(product.getId()) == null) {
                productDetails = product2ProductDetails(product);
                productDetails.setCountOfAProduct(1);
                mapProductDetails.put(product.getId(), productDetails);
            } else {
                productDetails = mapProductDetails.get(product.getId());
                int count = productDetails.getCountOfAProduct() + 1;
                productDetails.setCountOfAProduct(count);
                mapProductDetails.put(product.getId(), productDetails);
            }
        }
    }

    private ProductDetails product2ProductDetails(Product product) {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductRef(product.getId());
        productDetails.setCategory(product.getCategory());
        productDetails.setProductName(product.getProductName());
        productDetails.setProductPrice(product.getProductPrice());
        return productDetails;
    }
}
