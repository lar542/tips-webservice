package com.tips.oauth2.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CompositeFilter;

import com.tips.oauth2.social.SocialService;
import com.tips.oauth2.social.google.GoogleOAuth2ClientAuthenticationProcessingFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@EnableOAuth2Client
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final OAuth2ClientContext oauth2ClientContext;
	private final SocialService socialService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//임시
		http.csrf().disable();
    	http.headers().frameOptions().disable();
		
		http.antMatcher("/**") //모든 요청은 기본적으로 보호
			.authorizeRequests().antMatchers("/", "/login**", "/h2-console/**").permitAll() //홈페이지와 로그인은 보안 해제
			.anyRequest().authenticated() //다른 모든 곳에는 인증된 사용자가 필요
			.and()
			.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
			.and()
			.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
		
		//logout
		http.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.permitAll();
	}
	
	/**
	 * 인증 요청에 따른 리다이렉션을 위한 빈 등록
	 * @param filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100); //Spring Security filter보다 우선순위를 낮게 설정
		return registration;
	}
	
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
//		filters.add(ssoFilter(naver(), new NaverOAuth2ClientAuthenticationProcessingFilter(socialService)));
		filters.add(ssoFilter(google(), new GoogleOAuth2ClientAuthenticationProcessingFilter(socialService)));
		filter.setFilters(filters);
		return filter;
	}
	
	private Filter ssoFilter(ClientResources client, OAuth2ClientAuthenticationProcessingFilter filter) {
		//인증서버에서 OAuth2 access token을 획득하고
		//인증 객체를 SecurityConterxt에 로드하는 데 사용할 수 있는 OAuth2 클라이언트 필터
//		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(restTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
		tokenServices.setRestTemplate(restTemplate); //setRestTemplate으로 OAuth2 인증 REST 요청을 만들 수 있는 RestTemplate 객체를 지정
		filter.setTokenServices(tokenServices);
		return filter;
	}
	
	/**
	 * properties 들을 빈으로 등록
	 */
	@Bean
	@ConfigurationProperties("naver")
	public ClientResources naver() {
		return new ClientResources();
	}
	
	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
	  return new ClientResources();
	}
}
