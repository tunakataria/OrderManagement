package com.birlasoft.cartservice.config;

import com.birlasoft.cartservice.context.UserServiceReqContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class ContextConfig {

    @Bean
    @RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserServiceReqContext userServiceReqContext(){
        return  new UserServiceReqContext();
    }
}
