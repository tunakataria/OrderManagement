package com.birlasoft.authservice.api;

import com.birlasoft.authservice.models.UserRequest;
import com.birlasoft.authservice.models.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth-service/V1")
public interface ManageUserApi {

     @PostMapping("/add")
     UserResponse add(@RequestBody UserRequest userRequest);
     @PostMapping("/delete")
     UserResponse delete(@RequestBody  UserRequest userRequest);
}


