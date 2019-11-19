package com.yunfeng.security.core.properties;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-12
 */
@Data
public class BrowserProperties {

    private String loginPage = "/imooc-signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;
    /**
     * 记住我的时间 默认1小时
     */
    private int rememberMeSeconds = 3600;
}
