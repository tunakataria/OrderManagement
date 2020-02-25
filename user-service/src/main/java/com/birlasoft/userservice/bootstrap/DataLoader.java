package com.birlasoft.userservice.bootstrap;

import com.birlasoft.domain.User;
import com.birlasoft.userservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        //TODO create a user and persist in H2-DB
        User user = new User();
        user.setUserName("hitesh");
        userRepository.save(user);
        LOG.info("################\n"
                + "################\n"
                + "################\n"
                + user.getId() + " was added successfully"
                + "################\n"
                + "################\n");


    }
}
