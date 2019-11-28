package com.yunfeng.app.controller;

import com.yunfeng.app.social.AppSignUpUtils;
import com.yunfeng.security.core.social.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppSecurityController {
 
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
 
	@Autowired
	private AppSignUpUtils appSignUpUtils;
 
	@GetMapping("/social/signUp")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
 
		ConnectionData connectionData=connection.createData();
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
 
		appSignUpUtils.saveConnectionData(new ServletWebRequest(request), connectionData);
		return userInfo;
	}
 
}