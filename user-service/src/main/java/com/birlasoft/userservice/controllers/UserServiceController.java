package com.birlasoft.userservice.controllers;

import com.birlasoft.userservice.api.IUserService;
import com.birlasoft.userservice.models.BaseResponse;
import com.birlasoft.userservice.models.LoginRequest;
import com.birlasoft.userservice.services.ValidateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserServiceController implements IUserService {
    @Autowired
    private ValidateUserService validateUserService;

    @Override
    public BaseResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return validateUserService.process(loginRequest);
    }
}
