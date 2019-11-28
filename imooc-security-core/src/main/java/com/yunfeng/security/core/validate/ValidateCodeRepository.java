package com.yunfeng.security.core.validate;

import com.yunfeng.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-28
 */

public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @param request
     * @param captchaType
     * @return
     */
    ValidateCode get(ServletWebRequest request,  ValidateCodeType captchaType);

    /**
     * 移除验证码
     * @param request
     * @param validateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);

}
