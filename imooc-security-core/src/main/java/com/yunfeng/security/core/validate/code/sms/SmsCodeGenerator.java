package com.yunfeng.security.core.validate.code.sms;

import com.yunfeng.security.core.properties.SecurityProperties;
import com.yunfeng.security.core.validate.code.ValidateCode;
import com.yunfeng.security.core.validate.code.ValidateCodeGenerator;
import com.yunfeng.security.core.validate.code.image.ImageCode;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-16
 */
@Data
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public SmsCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsCode(code, securityProperties.getCode().getSms().getExpireIn());
    }



}
