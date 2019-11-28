package com.yunfeng.app.social;

import com.yunfeng.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-28
 */

public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
    }
}
