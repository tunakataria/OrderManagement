package com.birlasoft.productservice.exceptions;

import lombok.Data;

@Data
public class ProcessingException extends RuntimeException {

    private String message;

    public ProcessingException(String message) {
        super(message);
        this.message = message;
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }


}
