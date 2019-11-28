package com.yunfeng.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-28
 */

public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
