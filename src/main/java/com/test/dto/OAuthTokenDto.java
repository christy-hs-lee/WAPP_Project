package com.test.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuthTokenDto {
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;
    private String scope	;

}
