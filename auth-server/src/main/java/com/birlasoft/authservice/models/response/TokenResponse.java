package com.birlasoft.authservice.models.response;

import com.birlasoft.authservice.models.BaseAuthResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenResponse extends BaseAuthResponse {

    private String issuer;

}
