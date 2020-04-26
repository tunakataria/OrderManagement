package com.birlasoft.authservice.repository;

import com.birlasoft.authservice.domain.UserVerbose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserVerbose, Long> {

    @Query("SELECT uv from UserVerbose uv where uv.userName = :matcher")
    UserVerbose findByUserName(@Param("matcher") String matcher);

}
