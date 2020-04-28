package com.jincou.processor.properties.sms;

import com.jincou.processor.properties.CodeProperties;
import lombok.Data;

/**
 * @Description: 短信验证码属性
 *
 * @author xub
 * @date 2020/4/27 下午1:43
 */
@Data
public class SmsCodeProperties extends CodeProperties {

    public SmsCodeProperties() {
        setUrl("/mobie");
    }

}
