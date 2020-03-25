package com.birlasoft.authservice.service;

import com.birlasoft.authservice.domain.UserVerbose;

public interface IVerifyUserService {


    UserVerbose verify(String userName, String password);
}
