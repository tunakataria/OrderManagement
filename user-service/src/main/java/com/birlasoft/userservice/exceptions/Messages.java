package com.birlasoft.userservice.exceptions;

import lombok.Getter;

@Getter
public enum Messages {
    USER_NOT_FOUND("User not found!!");
    private String message;

    Messages(String message) {
        this.message = message;
    }
}
