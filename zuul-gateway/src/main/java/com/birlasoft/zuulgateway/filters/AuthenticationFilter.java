package com.birlasoft.zuulgateway.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.birlasoft.zuulgateway.configuration.JWTConfiguration;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.birlasoft.zuulgateway.filters.AuthenticationFilter.Accesses.CAN_ADD;



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

            if (Boolean.parseBoolean(decodedJWT.getClaims().get(CAN_ADD.name()).asString())) {
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
                .withIssuer(jwtConfiguration.getIssuer())
                .build(); //Reusable verifier instance
        return verifier.verify(jwt);
    }

    @Getter
    enum Accesses {
        CAN_ADD(true),
        CAN_DELETE(true),
        CAN_ORDER(true);
        private boolean granted;
        Accesses(boolean b) {
            this.granted = b;
        }


    }

}


