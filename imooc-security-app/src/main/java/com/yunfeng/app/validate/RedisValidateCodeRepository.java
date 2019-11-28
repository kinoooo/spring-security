package com.yunfeng.app.validate;

import com.yunfeng.security.core.validate.ValidateCodeRepository;
import com.yunfeng.security.core.validate.ValidateCodeType;
import com.yunfeng.security.core.validate.code.ValidateCode;
import com.yunfeng.security.core.validate.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    private final String DEVICE_ID = "deviceId";
    private final Integer EXPIRES_IN = 30;

    @Override
    public void save(ServletWebRequest servletWebRequest, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        String key = buildKey(servletWebRequest, validateCodeType);
        redisTemplate.opsForValue().set(key, validateCode, EXPIRES_IN, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            return null;
        }
        return (ValidateCode) o;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        String key = buildKey(request, codeType);
        redisTemplate.delete(key);
    }

    private String buildKey(ServletWebRequest servletWebRequest, ValidateCodeType validateCodeType) {
        String deviceId = servletWebRequest.getRequest().getHeader(DEVICE_ID);
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在Header中携带设备ID");
        }
        return "code:" + validateCodeType.toString().toLowerCase() + ":" + deviceId;
    }

}
