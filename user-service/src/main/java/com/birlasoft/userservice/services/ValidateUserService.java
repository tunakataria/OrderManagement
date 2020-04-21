package com.birlasoft.userservice.services;

import com.birlasoft.domain.user.User;
import com.birlasoft.userservice.models.BaseResponse;
import com.birlasoft.userservice.models.ErrorResponse;
import com.birlasoft.userservice.models.LoginRequest;
import com.birlasoft.userservice.models.LoginResponse;
import com.birlasoft.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.birlasoft.userservice.exceptions.Messages.USER_NOT_FOUND;

@Service
public class ValidateUserService {
    @Autowired
    private UserRepository userRepository;

    private Boolean userExists(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    public BaseResponse process(LoginRequest loginRequest) {
        return userExists(loginRequest.getUserId()) ? LoginResponse.builder().userAuthToken("ValidToken").build() :
                ErrorResponse.builder().errorMessage(USER_NOT_FOUND.getMessage()).build();
    }
}
