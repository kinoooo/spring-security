package com.yunfeng.security.core.validate.code.sms;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-18
 */

public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机：" + mobile + "发送短信验证码：" + code);
    }
}
