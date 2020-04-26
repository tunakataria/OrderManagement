package com.birlasoft.authservice.service;

import com.birlasoft.authservice.models.UserRequest;
import com.birlasoft.authservice.models.UserResponse;

public interface IManageUserService {

     UserResponse add(UserRequest userRequest);
     UserResponse delete(UserRequest userRequest);

}
