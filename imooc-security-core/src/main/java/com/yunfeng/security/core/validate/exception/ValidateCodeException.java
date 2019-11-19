package com.yunfeng.security.core.validate.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *
 * </p>
 *
 * @author yunfeng
 * @since 2019-11-13
 */

public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
