package com.yunfeng.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>
 * 校验码处理器，封装不同的校验码处理逻辑
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-18
 */

public interface ValidateProcessor {

    /**
     * 验证码放入session的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    /**
     * 创建校验码
     * @param request request response 都可以
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;
}
