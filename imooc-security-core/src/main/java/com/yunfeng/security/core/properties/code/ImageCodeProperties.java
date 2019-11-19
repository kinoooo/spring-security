package com.yunfeng.security.core.properties.code;

import lombok.Data;

/**
 * <p>
 * 图形验证码的默认配置
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-16
 */
@Data
public class ImageCodeProperties extends CodeProperties{

    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

}
