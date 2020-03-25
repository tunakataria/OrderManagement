package com.birlasoft.authservice.api;


import com.birlasoft.authservice.models.BaseAuthResponse;
import com.birlasoft.authservice.models.request.GenerateTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping({"/auth-service/V1"})
public interface IAuthService {

    @PostMapping({"/generate"})
    ResponseEntity<? extends BaseAuthResponse> generate(@RequestBody GenerateTokenRequest generateTokenRequest);

    @GetMapping({"/verify"})
    ResponseEntity<? extends BaseAuthResponse> verify(@RequestParam("token") String token);

}
