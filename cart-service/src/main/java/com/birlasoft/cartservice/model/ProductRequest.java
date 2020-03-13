package com.birlasoft.cartservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ProductRequest {
    private Long userId;
    private Long productId;
    private UpdateType TYPE;

    public enum UpdateType {
        REMOVAL,
        ADDITION,
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", TYPE=" + TYPE +
                '}';
    }
}
