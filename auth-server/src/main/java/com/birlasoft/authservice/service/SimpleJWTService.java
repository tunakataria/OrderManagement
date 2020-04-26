package com.birlasoft.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.birlasoft.authservice.config.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleJWTService implements JWTService {

   @Autowired
   private JWTConfig jwtConfig;

    @Override
    public String encode(Map<String, String> claimsHolder) {
        JWTCreator.Builder jwtBuilder = JWT.create();

        claimsHolder.entrySet().forEach(entry -> {
            jwtBuilder.withClaim(entry.getKey(), entry.getValue());
        });
        return jwtBuilder.withIssuer(jwtConfig.getISSUER()).sign(Algorithm.HMAC256(jwtConfig.getSECRET_KEY()));
    }

    @Override
    public DecodedJWT decode(String jwt) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfig.getSECRET_KEY()))
                .withIssuer(jwtConfig.getISSUER())
                .build(); //Reusable verifier instance
        return verifier.verify(jwt);
    }
}
