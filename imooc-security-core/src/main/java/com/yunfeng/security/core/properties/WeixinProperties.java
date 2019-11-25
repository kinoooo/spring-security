package com.yunfeng.security.core.properties;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-21
 */

@Data
public class WeixinProperties {

    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    private String providerId = "weixin";
}
