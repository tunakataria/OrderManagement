package com.birlasoft.authservice.service;

import com.birlasoft.authservice.dao.UserVerboseDao;
import com.birlasoft.authservice.domain.Role;
import com.birlasoft.authservice.domain.UserVerbose;
import com.birlasoft.authservice.models.UserRequest;
import com.birlasoft.authservice.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageUserService implements IManageUserService {

    @Autowired
    private UserVerboseDao userVerboseDao;


    @Override
    public UserResponse add(UserRequest userRequest) {
        userVerboseDao.add(getUserVerbose(userRequest));
        return generateUserResponse(UserResponse.Operation.SUCCESS,
                "User " + userRequest.getUsername() + " created successfully");

    }

    @Override
    public UserResponse delete(UserRequest userRequest) {
        userVerboseDao.delete(getUserVerbose(userRequest));
        return generateUserResponse(UserResponse.Operation.SUCCESS,
                "User " + userRequest.getUsername() + " deleted successfully");
    }

    public UserVerbose getUserVerbose(UserRequest userRequest) {
        return new UserVerbose(userRequest.getUsername(), Role.ADMIN, userRequest.getPasswd());
    }

    private UserResponse generateUserResponse(UserResponse.Operation status, String message) {
        return UserResponse.builder().message(message).status(status).build();
    }


}
