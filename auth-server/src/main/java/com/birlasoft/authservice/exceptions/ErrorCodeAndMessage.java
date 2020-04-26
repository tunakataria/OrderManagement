package com.birlasoft.authservice.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCodeAndMessage {
    USER_NOT_FOUND(404,"404.1", "USER_NOT_FOUND"),
    USER_NAME_TAKEN(301,"301.1", "USER_NAME_TAKEN"),
    WRONG_USER_NAME_OR_PASSWORD(401, "401.1", "WRONG_USER_NAME_OR_PASSWORD");
    private int httpCode;
    private String errorCode;
    private String errorMessage;

    ErrorCodeAndMessage(int httpCode, String errorCode, String errorMessage) {
        this.httpCode=httpCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}