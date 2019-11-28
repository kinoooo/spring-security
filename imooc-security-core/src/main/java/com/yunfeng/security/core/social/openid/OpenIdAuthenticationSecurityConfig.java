package com.yunfeng.security.core.social.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
 
	@Autowired
	private SocialUserDetailsService userDetailsService;
 
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
 
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
 
	@Override
	public void configure(HttpSecurity http) throws Exception {
		OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setUsersConnectionRepository(usersConnectionRepository);
 
		OpenIdAuthenticationFilter filter = new OpenIdAuthenticationFilter();
 
		filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		filter.setAuthenticationFailureHandler(authenticationFailureHandler);
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
 
		//密码登录后置过滤
		http.
				authenticationProvider(provider)
				.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
	}
 
}
