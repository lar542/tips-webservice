package com.tips.oauth2.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * properties를 편하게 사용하기 위한 클래스
 */
public class ClientResources {

	@NestedConfigurationProperty //해당 타입의 메타 데이터를 크롤링하는 어노테이션
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
	
	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();
	
	public AuthorizationCodeResourceDetails getClient() {
		return client;
	}
	
	public ResourceServerProperties getResource() {
		return resource;
	}
}
