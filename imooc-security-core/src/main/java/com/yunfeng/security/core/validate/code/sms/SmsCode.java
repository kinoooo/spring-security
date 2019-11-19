package com.yunfeng.security.core.validate.code.sms;

import com.yunfeng.security.core.validate.code.ValidateCode;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-19
 */

public class SmsCode extends ValidateCode {

    public SmsCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }
}
