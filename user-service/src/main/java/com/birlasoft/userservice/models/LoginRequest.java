package com.birlasoft.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {
    @NotNull
    private Long userId;
    @NotNull
    private String userPass;

}
