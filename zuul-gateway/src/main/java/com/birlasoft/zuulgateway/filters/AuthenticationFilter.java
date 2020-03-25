package com.birlasoft.zuulgateway.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.birlasoft.zuulgateway.configuration.JWTConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.birlasoft.zuulgateway.filters.AuthenticationFilter.Accesses.CAN_ADD_PRODUCT;



public class AuthenticationFilter extends OncePerRequestFilter {

    private JWTConfiguration jwtConfiguration;

    public AuthenticationFilter(JWTConfiguration jwtConfiguration) {
        this.jwtConfiguration=jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(jwtConfiguration.getHeaderName());
        if(authHeader==null){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        try{
        if(authHeader!=null) {
            String token = optReturn(authHeader, ($) -> $.startsWith(jwtConfiguration.getPrefix()), ($) -> {
                return $.replace(jwtConfiguration.getPrefix(), "");
            });

            DecodedJWT decodedJWT = decode(token.trim());

            if (Boolean.parseBoolean(decodedJWT.getClaims().get(CAN_ADD_PRODUCT.getValue()).asString())) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        null, null, Arrays.asList(new SimpleGrantedAuthority("ADMIN"))
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }}catch (Exception exception){
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private <T> T optReturn(T t, Predicate<T> predicate, Function<T, T> function) {
        return predicate.test(t) ? function.apply(t) : t;
    }

    public DecodedJWT decode(String jwt) {
        Algorithm signingAlgo = Algorithm.HMAC256(jwtConfiguration.getSecretKey());
        JWTVerifier verifier = JWT.require(signingAlgo)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        return verifier.verify(jwt);
    }

    @Getter
    enum Accesses {
        CAN_ADD_PRODUCT("canAddProduct");

        Accesses(String value) {
            this.value = value;
        }

        private String value;


    }

}


