package com.birlasoft.authservice.dao;

import com.birlasoft.authservice.domain.UserVerbose;
import com.birlasoft.authservice.exceptions.ProcessingException;
import com.birlasoft.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.birlasoft.authservice.exceptions.ErrorCodeAndMessage.USER_NAME_TAKEN;


@Component
public class UserVerboseDao {

    @Autowired
    private UserRepository userRepository;

    public UserVerbose add(UserVerbose userVerbose) {
        Optional<UserVerbose> optUv = Optional.
                ofNullable(userRepository.findByUserName(userVerbose.getUserName()));

        if (optUv.isPresent()) {
          throw ProcessingException.builder().errorCodeAndMessage(USER_NAME_TAKEN).build();
        }
        return userRepository.save(userVerbose);
    }

    public void delete(UserVerbose userVerbose) {
        Optional<UserVerbose> optUv = Optional.ofNullable(userRepository.findByUserName(userVerbose.getUserName()));

        optUv.ifPresent(user -> userRepository.delete(user));

        if (!optUv.isPresent()) {
            throw new ProcessingException("The use you are trying to delete is not present");
        }
    }


}
