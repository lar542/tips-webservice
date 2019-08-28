package com.tips.oauth2.social.naver;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

import lombok.Getter;

@Getter
public class NaverUserDetails {

    private String name;
    private String email;
    private boolean email_verified;

    private long expiration;
    private String access_token;
    
    public void setAccessToken(OAuth2AccessToken accessToken) {
        this.access_token = accessToken.getValue();
        this.expiration = accessToken.getExpiration().getTime();
    }
}
