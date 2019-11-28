package com.yunfeng.security.core.properties.oauth;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-28
 */
@Data
public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};
    private String jwtSigningKey = "imooc";

}
