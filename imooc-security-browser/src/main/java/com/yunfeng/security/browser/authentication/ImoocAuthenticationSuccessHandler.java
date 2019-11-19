package com.yunfeng.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunfeng.security.core.properties.LoginResponseType;
import com.yunfeng.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *  {@link SavedRequestAwareAuthenticationSuccessHandler}是Spring Security默认的成功处理器
 * @author yunfeng
 *
 */
@Component("imoocAuthenticationSuccessHandler")
@Slf4j
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("登录成功");

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			// 如果用户自定义了处理成功后返回JSON（默认方式也是JSON），那么这里就返回JSON
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			// 如果用户定义的是跳转，那么就使用父类方法进行跳转
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
