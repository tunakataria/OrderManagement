package com.birlasoft.authservice.domain;

import com.birlasoft.authservice.domain.converters.Base64Convertor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_verbose")
public class UserVerbose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Convert(converter = Base64Convertor.class)
    private String password;

    public UserVerbose(String userName, Role role, String password) {
        this.userName = userName;
        this.role = role;
        this.password = password;
    }
}
