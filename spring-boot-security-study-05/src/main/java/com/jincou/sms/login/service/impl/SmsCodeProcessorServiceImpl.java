package com.jincou.sms.login.service.impl;

import com.jincou.sms.login.dto.sms.SmsCode;
import com.jincou.sms.login.service.AbstractValidateCodeProcessorService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: 短信验证码 发送 具体实现类
 *
 * @author xub
 * @date 2020/4/28 上午10:51
 */
@Component("smsCodeProcessorService")
public class SmsCodeProcessorServiceImpl extends AbstractValidateCodeProcessorService<SmsCode> {
    private static final String SMS_CODE_PARAM_NAME = "mobile";

    @Override
    protected void send(ServletWebRequest request, SmsCode smsCode) throws Exception {
        //这里有一个参数也是前端需要传来的 就是用户的手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SMS_CODE_PARAM_NAME);
        // 这里仅仅写个打印，具体逻辑一般都是调用第三方接口发送短信
        System.out.println("向手机号为：" + mobile + "的用户发送验证码：" + smsCode.getCode());
    }
}
