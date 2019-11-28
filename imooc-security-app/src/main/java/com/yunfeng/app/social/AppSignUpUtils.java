package com.yunfeng.app.social;

import com.sun.deploy.association.utility.AppConstants;
import com.yunfeng.app.exception.AppSecretException;
import com.yunfeng.security.core.constants.SecurityConstants;
import com.yunfeng.security.core.validate.ValidateCodeType;
import com.yunfeng.security.core.validate.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Component
public class AppSignUpUtils {
 
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
 
	/**
	 * 保存社交信息到redis
	 * @param request
	 * @param connectionData
	 */
	public void saveConnectionData(ServletWebRequest request, ConnectionData connectionData) {
		redisTemplate.opsForValue().set(buildKey(request), connectionData);
	}
 
	/**
	 *  社交用户信息入库
	 * @param request
	 * @param userId
	 */
	public void doPostSignUp(String userId, WebRequest request) {
		String key = buildKey(request);
		if(!redisTemplate.hasKey(key)){
			throw new AppSecretException("无法找到缓存的用户社交账号信息");
		}
		ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
 
		Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
				.createConnection(connectionData);
 
		usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
 
		redisTemplate.delete(key);
	}
 
 
	private String buildKey(WebRequest request) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new AppSecretException("设备id参数不能为空");
		}
		return "yunfeng:security:social.connect." + deviceId;
	}

 
}
