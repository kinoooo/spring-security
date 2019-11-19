package com.yunfeng.security.core.validate.config;

import com.yunfeng.security.core.properties.SecurityProperties;
import com.yunfeng.security.core.validate.code.ValidateCodeGenerator;
import com.yunfeng.security.core.validate.code.image.ImageCodeGenerator;
import com.yunfeng.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.yunfeng.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-16
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @ConditionalOnMissingBean 不存在名字为"imageCodeGenerator"的bean 的时候用代码里的
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }


    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
