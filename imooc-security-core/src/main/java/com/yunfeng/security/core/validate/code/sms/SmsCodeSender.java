package com.yunfeng.security.core.validate.code.sms;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-18
 */

public interface SmsCodeSender {

    void send(String mobile, String code);
}
