package com.birlasoft.authservice.service;

import com.auth0.jwt.interfaces.Claim;
import com.birlasoft.authservice.models.BaseAuthResponse;
import com.birlasoft.authservice.models.request.GenerateTokenRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProcessingService {

    ResponseEntity<? extends BaseAuthResponse> process(GenerateTokenRequest generateTokenRequest);


    ResponseEntity<? extends BaseAuthResponse> process(String encodedJwt);


}
