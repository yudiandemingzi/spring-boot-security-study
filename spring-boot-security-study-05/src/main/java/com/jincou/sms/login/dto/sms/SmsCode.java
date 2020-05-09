package com.jincou.sms.login.dto.sms;


import com.jincou.sms.login.dto.ValidateCode;

import java.time.LocalDateTime;

/**
  * @Description: 对于短信验证码而言只需要父类两个属性就够了
  *
  * @author xub
  * @date 2020/4/27 上午9:42
  */
public class SmsCode extends ValidateCode {

    private String mobile;

    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SmsCode(String code, int expireIn,String mobile) {
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
