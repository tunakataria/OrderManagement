package com.birlasoft.authservice.controller;

import com.birlasoft.authservice.api.ManageUserApi;
import com.birlasoft.authservice.models.UserRequest;
import com.birlasoft.authservice.models.UserResponse;
import com.birlasoft.authservice.service.IManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ManageUserController  implements ManageUserApi {

    @Autowired
    private IManageUserService manageUserService;

    @Override
    public UserResponse add(@Valid UserRequest userRequest) {
        return manageUserService.add(userRequest);
    }

    @Override
    public UserResponse delete(@Valid UserRequest userRequest) {
        return manageUserService.delete(userRequest);
    }
}
