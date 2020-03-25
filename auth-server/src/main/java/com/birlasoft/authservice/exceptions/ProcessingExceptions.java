package com.birlasoft.authservice.exceptions;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

public class ProcessingExceptions extends RuntimeException {

    private String customMessage;

    private Throwable cause;

    public ProcessingExceptions(String message, Throwable cause) {
        super(message, cause);
        this.customMessage = message;
    }


}
