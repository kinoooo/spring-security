package com.yunfeng.app.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-28
 */

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        info.put("company", "yunfeng");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
