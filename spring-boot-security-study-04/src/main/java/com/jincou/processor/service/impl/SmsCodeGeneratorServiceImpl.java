package com.jincou.processor.service.impl;

import com.jincou.processor.config.ValidateCodeProperties;
import com.jincou.processor.dto.sms.SmsCode;

import com.jincou.processor.service.ValidateCodeGeneratorService;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 *
 * @author lemon
 * @date 2018/4/7 上午11:09
 */
@Data
@Component("smsCodeGeneratorService")
public class SmsCodeGeneratorServiceImpl implements ValidateCodeGeneratorService {

    @Autowired
    private ValidateCodeProperties validateCodeProperties;


    @Override
    public SmsCode generate(ServletWebRequest request) {
        //生成随机数
        String code = RandomStringUtils.randomNumeric(validateCodeProperties.getSms().getLength());
        return new SmsCode(code, validateCodeProperties.getSms().getExpireIn());
    }
}
