package com.birlasoft.cartservice.model;

import com.birlasoft.cartservice.command.CartDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CartResponse {
    private CartDto cart;
}
