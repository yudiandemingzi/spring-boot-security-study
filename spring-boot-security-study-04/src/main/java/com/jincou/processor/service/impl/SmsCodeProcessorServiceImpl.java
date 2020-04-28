package com.jincou.processor.service.impl;

import com.jincou.processor.dto.sms.SmsCode;
import com.jincou.processor.service.AbstractValidateCodeProcessorService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author lemon
 * @date 2018/4/17 下午11:41
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessorServiceImpl extends AbstractValidateCodeProcessorService<SmsCode> {
    private static final String SMS_CODE_PARAM_NAME = "mobile";

    @Override
    protected void send(ServletWebRequest request, SmsCode smsCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SMS_CODE_PARAM_NAME);
        // 这里仅仅写个打印，具体逻辑一般都是调用第三方接口发送短信
        System.out.println("向手机号为：" + mobile + "的用户发送验证码：" + smsCode.getCode());
    }
}
