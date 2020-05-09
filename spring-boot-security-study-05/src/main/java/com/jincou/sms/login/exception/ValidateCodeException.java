package com.jincou.sms.login.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 自定义异常
 *
 * @author xub
 * @date 2020/4/27 下午12:24
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
