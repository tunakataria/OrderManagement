package com.birlasoft.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
public class SimpleJWTService implements JWTService {

    private static final String SECRET = "MySecret";

    private static final Algorithm signingAlgo = Algorithm.HMAC256(SECRET);

    @Override
    public String encode(Map<String, String> claimsHolder) {
        JWTCreator.Builder jwtBuilder = JWT.create();

        claimsHolder.entrySet().forEach(entry -> {
            jwtBuilder.withClaim(entry.getKey(), entry.getValue());
        });
        return jwtBuilder.withIssuer("auth0").sign(signingAlgo);
    }

    @Override
    public DecodedJWT decode(String jwt) {
        JWTVerifier verifier = JWT.require(signingAlgo)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        return verifier.verify(jwt);
    }
}
