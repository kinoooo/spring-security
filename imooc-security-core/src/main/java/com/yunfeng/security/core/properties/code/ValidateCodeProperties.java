package com.yunfeng.security.core.properties.code;

import com.yunfeng.security.core.properties.code.ImageCodeProperties;
import com.yunfeng.security.core.properties.code.SmsCodeProperties;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-16
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();


}
