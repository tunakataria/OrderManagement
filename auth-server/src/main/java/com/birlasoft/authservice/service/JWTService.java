package com.birlasoft.authservice.service;

import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public interface JWTService {

    String encode(Map<String ,String> claimsHolder);

    DecodedJWT decode(String jwt);
}
