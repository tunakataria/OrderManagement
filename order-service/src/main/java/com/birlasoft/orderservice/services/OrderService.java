package com.birlasoft.orderservice.services;

import com.birlasoft.domain.Cart;
import com.birlasoft.domain.OrderData;
import com.birlasoft.domain.ProductDetails;
import com.birlasoft.orderservice.extapi.CartApiProxy;
import com.birlasoft.orderservice.models.OrderResponse;
import com.birlasoft.orderservice.repositories.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartApiProxy cartApiProxy;

    @Autowired
    private OrderDataService orderDataService;

    @Resource
    private HttpServletRequest currentRequest;


    public OrderResponse order(Long userId) {
        Cart cart = getCart(userId);
        deleteProduct(cart.getId());
        OrderData orderData = createOrder(cart);
        return createOrderReponse(orderData);
    }

    private Cart getCart(Long userId) {
        return cartApiProxy.cart(userId, currentRequest.getHeader(HttpHeaders.AUTHORIZATION));
    }

    private void deleteProduct(Long cartId) {
        cartApiProxy.deleteProducts(cartId, currentRequest.getHeader(HttpHeaders.AUTHORIZATION));
    }

    private OrderData createOrder(Cart cart) {
        Long userId = cart.getUserId();
        Map<Long, ProductDetails> productDetailsHashMap = cart.getProductCountMap();
        Set<ProductDetails> productDetailsList = productDetailsHashMap.entrySet().stream()
                .map(entr -> entr.getValue()).map(value -> {
                    value.setId(null);
                    return value;
                }).collect(Collectors.toSet());
        Long orderTotal = productDetailsList.stream()
                .map(productDetails ->
                        Integer.parseInt(productDetails.getProductPrice()) * productDetails.getCountOfAProduct())
                .collect(Collectors.summingLong(Integer::intValue));
        OrderData orderData = new OrderData();
        orderData.setUserId(userId);
        orderData.setProductDetails(productDetailsList);
        orderData.setOrderTotal(orderTotal);
        return orderDataService.save(orderData);
    }

    private OrderResponse createOrderReponse(OrderData orderData) {
        return OrderResponse.builder().orderId(orderData.getId())
                .orderTotal(orderData.getOrderTotal())
                .userId(orderData.getUserId())
                .productDetails(orderData.getProductDetails()).build();
    }
}
