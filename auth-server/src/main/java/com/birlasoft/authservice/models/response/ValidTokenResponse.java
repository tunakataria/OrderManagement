package com.birlasoft.authservice.models.response;

import com.birlasoft.authservice.models.BaseAuthResponse;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValidTokenResponse extends BaseAuthResponse {

    private Map<String, String> claims;
}
