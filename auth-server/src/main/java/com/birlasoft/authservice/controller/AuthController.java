package com.birlasoft.authservice.controller;

import com.birlasoft.authservice.api.IAuthService;
import com.birlasoft.authservice.models.BaseAuthResponse;
import com.birlasoft.authservice.models.request.GenerateTokenRequest;
import com.birlasoft.authservice.models.response.TokenResponse;
import com.birlasoft.authservice.models.response.ValidTokenResponse;
import com.birlasoft.authservice.service.IRequestHandler;
import com.birlasoft.authservice.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthController implements IAuthService {

    @Autowired
    private IRequestHandler iRequestHandler;

    @Override
    public ResponseEntity<? extends BaseAuthResponse> generate(@Valid GenerateTokenRequest generateTokenRequest) {
          return iRequestHandler.process(generateTokenRequest);
    }

    @Override
    public ResponseEntity<? extends BaseAuthResponse> verify(String token) {
        return iRequestHandler.process(token);
    }
}
