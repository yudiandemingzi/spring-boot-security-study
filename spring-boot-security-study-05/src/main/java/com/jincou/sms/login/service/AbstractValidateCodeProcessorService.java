package com.jincou.sms.login.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
  * @Description: 为什么要单独可一个抽象类，因为对于短信验证码 还是 图形验证码来讲processor方法来讲,
  * 对于这三件事而言第一步和第二步是可以抽离出来的,只有第三步是需要单独实现的
  *
  * @author xub
  * @date 2020/4/27 上午11:06
  */
@Component
public abstract class AbstractValidateCodeProcessorService<C> implements ValidateCodeProcessorService {

    private static final String SEPARATOR = "/code/";

    /**
     * 操作session的工具集
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 这是Spring的一个特性，就是在项目启动的时候会自动收集系统中 {@link ValidateCodeGeneratorService} 接口的实现类对象
     *
     * key为bean name
     */
    @Autowired
    private Map<String, ValidateCodeGeneratorService> validateCodeGeneratorMap;

    @Override
    public void processor(ServletWebRequest request) throws Exception {
        //第一件事
        C validateCode = generate(request);
        //第二件事
        save(request, validateCode);
        //第三件事
        send(request, validateCode);
    }

    /**
     * 生成验证码
     *
     */
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        //这里 image+CodeGenerator = imgCodeGenerator 对应的就是ImageCodeGeneratorServiceService
        ValidateCodeGeneratorService validateCodeGenerator = validateCodeGeneratorMap.get(type.concat(ValidateCodeGeneratorService.CODE_GENERATOR));
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存验证码到session中
     *
     */
    private void save(ServletWebRequest request, C validateCode) {
        //这里也是封装了一下
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX.concat(getProcessorType(request).toUpperCase()), validateCode);
    }

    /**
     * 发送验证码 （只有发送验证码是需要自己去实现的。）
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 获取请求URL中具体请求的验证码类型
     *
     */
    private String getProcessorType(ServletWebRequest request) {
        // 获取URI分割后的第二个片段 (/code/image 通过/code/ 切割后就只剩下 image
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), SEPARATOR);
    }
}
