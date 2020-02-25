package com.birlasoft.userservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse extends BaseResponse {
    private String userAuthToken;
}
