package com.jincou.sms.login.service;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 *
 * @author lemon
 * @date 2018/4/17 下午9:46
 */
public interface ValidateCodeProcessorService {

    /**
     * 因为现在有两种验证码，所以存放到seesion的key不能一样，所以前缀+具体type
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /**
     * 通过也是 type+CODE_PROCESSOR获取对于的bean
     */
    String CODE_PROCESSOR = "CodeProcessorService";

    /**
     * 这个接口要做三件事
     * 1、获取验证码。
     * 2、将验证码存入session
     * 3、将验证码信息通过短信或者图形验证码发送出去。
     * （将spring-boot-security-study-03接口里的那三步进行里封装）
     *
     */
    void processor(ServletWebRequest request) throws Exception;
}
