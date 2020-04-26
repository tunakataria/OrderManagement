package com.birlasoft.authservice.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class UserResponse {

    private Operation status;
    private String message;
    public enum Operation {
        SUCCESS,
        FAILED;
    }
}
