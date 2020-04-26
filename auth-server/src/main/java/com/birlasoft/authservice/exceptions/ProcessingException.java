package com.birlasoft.authservice.exceptions;


import lombok.*;

import java.sql.Statement;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProcessingException extends RuntimeException {
    private String customMessage;
    private Throwable cause;
    private ErrorCodeAndMessage errorCodeAndMessage;

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.customMessage = message;
    }

    public ProcessingException(String customMessage) {
        this.customMessage = customMessage;
    }
}
