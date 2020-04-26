package com.birlasoft.authservice.service;

import com.birlasoft.authservice.domain.UserVerbose;
import com.birlasoft.authservice.exceptions.ProcessingException;
import com.birlasoft.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.birlasoft.authservice.exceptions.ErrorCodeAndMessage.WRONG_USER_NAME_OR_PASSWORD;

@Service
public class VerifyUserService implements IVerifyUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVerbose verify(String userName, String password) {
        UserVerbose userVerbose = userRepository.findByUserName(userName);
        if(userVerbose.getPassword().equals(password)){
            return userVerbose;
        }
        throw ProcessingException.builder().errorCodeAndMessage(WRONG_USER_NAME_OR_PASSWORD).build();
    }
}
