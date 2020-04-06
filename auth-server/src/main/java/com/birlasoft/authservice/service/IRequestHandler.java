package com.birlasoft.authservice.service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.birlasoft.authservice.domain.UserVerbose;
import com.birlasoft.authservice.models.BaseAuthResponse;
import com.birlasoft.authservice.models.request.GenerateTokenRequest;
import com.birlasoft.authservice.models.response.TokenResponse;
import com.birlasoft.authservice.models.response.ValidTokenResponse;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IRequestHandler implements ProcessingService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private IVerifyUserService verifyUser;

    @Override
    public ResponseEntity<? extends BaseAuthResponse> process(GenerateTokenRequest generateTokenRequest) {
        UserVerbose verbose = verifyUser.verify(generateTokenRequest.getUserName(), generateTokenRequest.getPassword());
        String encoded = jwtService.encode(verbose.getAccesses());
       return generateResponse(generateAuthResponse(encoded));
    }

    @Override
    public ResponseEntity<? extends BaseAuthResponse> process(String encodedJwt) {
         final DecodedJWT decodedJWT = jwtService.decode(encodedJwt);
         return generateResponse(generateValidTokenResponse(decodedJWT));

    }

    private ResponseEntity<? extends BaseAuthResponse> generateResponse(BaseAuthResponse object) {
        ResponseEntity<? extends BaseAuthResponse> responseEntity = new ResponseEntity(object, HttpStatus.OK);
        return responseEntity;
    }
    private BaseAuthResponse generateAuthResponse(String encodedJson) {
        final TokenResponse tokenResponse = TokenResponse.builder().issuer("OAuthSever").build();
        tokenResponse.setAuthToken(encodedJson);
        return tokenResponse;
    }

    private ValidTokenResponse generateValidTokenResponse(DecodedJWT decodedJWT){
        Map<String,String> claims = new HashMap<>();
        decodedJWT.getClaims().entrySet().forEach(entry->{claims.put(entry.getKey(), entry.getValue().asString());});
        return ValidTokenResponse.builder().claims(claims).build();
    }
}
