package com.yunfeng.security.core.validate.code;

import com.yunfeng.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-16
 */

public interface ValidateCodeGenerator {

    /**
     * 生成图片验证码
     *
     * @param request 请求
     * @return ImageCode实例对象
     */
    ValidateCode generate(ServletWebRequest request);
}
