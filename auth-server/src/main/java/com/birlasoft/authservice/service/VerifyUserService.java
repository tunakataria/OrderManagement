package com.birlasoft.authservice.service;

import com.birlasoft.authservice.domain.UserVerbose;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyUserService implements IVerifyUserService {

    public static final Map<String, Map<String,String>> userVersboseMaps = new HashMap<>();

    static {
        Map<String,String> userAccesses = new HashMap<String,String>();
        userAccesses.put("canAddProduct","true");
        userAccesses.put("canDeleteProduct","false");
        userAccesses.put("canPlaceOrder", "true");
        userVersboseMaps.put("hitesh", userAccesses);
    }

    @Override
    public UserVerbose verify(String userName, String password) {
        return new UserVerbose(userName, userVersboseMaps.get(userName));
    }
}
