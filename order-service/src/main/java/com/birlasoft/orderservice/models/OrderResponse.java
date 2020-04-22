package com.birlasoft.orderservice.models;

import com.birlasoft.domain.common.ProductDetails;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder

public class OrderResponse {

    private Long orderId;
    private Set<ProductDetails> productDetails;
    private Long orderTotal;
    private Long userId;
}
