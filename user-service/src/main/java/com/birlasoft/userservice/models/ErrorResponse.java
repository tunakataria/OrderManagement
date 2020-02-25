package com.birlasoft.userservice.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse extends BaseResponse {
    String errorMessage;
}
