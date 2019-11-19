/**
 * 
 */
package com.yunfeng.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunfeng.security.browser.support.SimpleResponse;
import com.yunfeng.security.core.properties.LoginResponseType;
import com.yunfeng.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *  {@link SimpleUrlAuthenticationFailureHandler}是Spring Boot默认的失败处理器
 * @author yunfeng
 *
 */
@Component("imoocAuthenctiationFailureHandler")
@Slf4j
public class ImoocAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;

	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("登录失败");
		
		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			// 如果用户自定义了处理成功后返回JSON（默认方式也是JSON），那么这里就返回JSON
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
		}else{
			// 如果用户配置为跳转，则跳到Spring Boot默认的错误页面
			super.onAuthenticationFailure(request, response, exception);
		}
		
		
	}

}
