package com.yunfeng.security.core.properties;

import com.yunfeng.security.core.constants.SecurityConstants;
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

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private String signUpUrl = "/imooc-signUp.html";

    private LoginResponseType loginType = LoginResponseType.JSON;
    /**
     * 记住我的时间 默认1小时
     */
    private int rememberMeSeconds = 3600;

    private SessionProperties session = new SessionProperties();
}
