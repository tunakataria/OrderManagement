package com.birlasoft.zuulgateway.configuration;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public class JWTConfiguration {

    @Value("${jwt.secretKey:secret}")
    private  String secretKey;

    @Value("${jwt.header.name:Authorization}")
    private String headerName;

    @Value("${jwt.header.prefix:Bearer}")
    private String prefix;

    @Value("${jwt.generator.uri:/**/generate/**}")
    private String tokenGeneratorURI;

    @Value("${jwt.token.issuer:authserver}")
    private String issuer;




}
