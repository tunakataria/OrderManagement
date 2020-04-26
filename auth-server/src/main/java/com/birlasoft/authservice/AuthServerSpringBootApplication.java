package com.birlasoft.authservice;

import com.birlasoft.authservice.domain.Role;
import com.birlasoft.authservice.domain.UserVerbose;
import com.birlasoft.authservice.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AuthServerSpringBootApplication {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerSpringBootApplication.class,args);
    }

    @Bean(name = "bootStrapLoader")
    public CommandLineRunner bootStrapLoader(){
        return ($)->{
            UserVerbose uv = new UserVerbose(1L, "hitesh", Role.ADMIN, "passwd");
            userRepository.save(uv);
        };
    }

}
