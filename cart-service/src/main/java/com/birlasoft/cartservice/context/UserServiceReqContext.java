package com.birlasoft.cartservice.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserServiceReqContext {
    private Long userId;
    private Long cartId;
}
