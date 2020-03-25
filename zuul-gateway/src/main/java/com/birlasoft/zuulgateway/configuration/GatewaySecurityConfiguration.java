package com.birlasoft.zuulgateway.configuration;

import com.birlasoft.zuulgateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class GatewaySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTConfiguration jwtConfiguration;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, res, exp) -> {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }).and().addFilterAfter(new AuthenticationFilter(jwtConfiguration), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getTokenGeneratorURI())
                .permitAll()
                .antMatchers("/**")
                .authenticated();
    }

    @Bean
    public JWTConfiguration jwtConfiguration() {
        return new JWTConfiguration();
    }
}
