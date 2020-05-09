package com.jincou.sms.login.config;

import com.jincou.sms.login.properties.img.ImageCodeProperties;
import com.jincou.sms.login.properties.sms.SmsCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
  * @Description: 把这个两个当成配置属性
  *
  * @author xub
  * @date 2020/4/25 上午10:17
  */
@Data
@ConfigurationProperties(prefix = "com.jincou.processor")
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();

}
