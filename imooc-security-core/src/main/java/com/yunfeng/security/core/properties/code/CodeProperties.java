package com.yunfeng.security.core.properties.code;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-19
 */

@Data
public class CodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码过期时间
     */
    private int expireIn = 60;

    /**
     * 需要验证码的url字符串，用英文逗号隔开
     */
    private String url;

}
