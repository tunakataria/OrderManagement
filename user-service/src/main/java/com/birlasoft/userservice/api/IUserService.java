package com.birlasoft.userservice.api;

import com.birlasoft.userservice.models.BaseResponse;
import com.birlasoft.userservice.models.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user-service/V1")
public interface IUserService {

    @PostMapping("/login")
    BaseResponse login(LoginRequest loginRequest);
}
