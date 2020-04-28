package com.jincou.processor.service;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码生成接口
 *
 * @author lemon
 * @date 2018/4/17 下午9:46
 */
public interface ValidateCodeProcessorService {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    String CODE_PROCESSOR = "CodeProcessor";

    /**
     * 这个接口要做三件事
     * 1、获取验证码。
     * 2、将验证码存入session
     * 3、将验证码信息通过短信或者图形验证码发送出去。
     * （将spring-boot-security-study-03里的那三步进行里封装）
     *
     */
    void processor(ServletWebRequest request) throws Exception;
}
