package com.tips.oauth2.social.userconnection;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tips.oauth2.social.facebook.FacebookUserDetails;
import com.tips.oauth2.social.google.GoogleUserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserConnection implements Serializable {

	@Id @GeneratedValue
	@Column(name = "USERCONNECTION_ID")
	private Long id;
	
	private String email;

	@Enumerated(EnumType.STRING)
	private ProviderType provider;
	
	@Column(name = "PROVIDER_ID", unique = true, nullable = false)
    private String providerId;
	
    private String displayName;

    private String profileUrl;

    private String imageUrl;

    private String accessToken;

    private long expireTime;
	
    @Builder
    private UserConnection(String email, ProviderType provider, String providerId, String displayName, String profileUrl, String imageUrl, String accessToken, long expireTime) {
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
	
    public static UserConnection valueOf(GoogleUserDetails userDetails) {
        return UserConnection.builder()
                .expireTime(userDetails.getExpiration())
                .accessToken(userDetails.getAccess_token())
                .providerId(userDetails.getSub())
                .email(userDetails.getEmail())
                .displayName(userDetails.getName())
                .imageUrl(userDetails.getPicture())
                .provider(ProviderType.GOOGLE)
                .profileUrl(userDetails.getProfile())
                .build();
    }
    
    public static UserConnection valueOf(FacebookUserDetails userDetails) {
        return UserConnection.builder()
                .expireTime(userDetails.getExpiration())
                .accessToken(userDetails.getAccess_token())
                .providerId(userDetails.getId())
                .email(userDetails.getEmail())
                .displayName(userDetails.getName())
                .imageUrl(userDetails.getImageUrl())
                .provider(ProviderType.FACEBOOK)
                .build();
    }
}
