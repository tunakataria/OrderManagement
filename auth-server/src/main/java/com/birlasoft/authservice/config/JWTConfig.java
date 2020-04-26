package com.birlasoft.authservice.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:/config/jwt-config.properties"),
        @PropertySource("classpath:/jwt-config.properties")})
@Data
public class JWTConfig {
    @Value("${jwt.secret.key:secret}")
    private  String SECRET_KEY;

    @Value("${jwt.signing.algo:HMCA256}")
    private  String SINGNING_ALGO;

    @Value("${jwt.token.issuer:authserver}")
    private  String ISSUER;

    @Value("${jwt.token.validity:300s}")
    private  String TTL;

}
