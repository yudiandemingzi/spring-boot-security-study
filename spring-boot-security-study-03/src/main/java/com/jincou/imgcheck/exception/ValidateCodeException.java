package com.jincou.imgcheck.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lemon
 * @date 2018/4/6 下午8:26
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
