package com.jincou.sms.login.properties.img;

import com.jincou.sms.login.properties.CodeProperties;
import lombok.Data;

/**
 * @Description: 图形验证码的默认配置
 *
 * @author xub
 * @date 2020/4/27 上午10:44
 */
@Data
public class ImageCodeProperties extends CodeProperties {

    public ImageCodeProperties() {

        setLength(4);
        setUrl("/login");
    }

    /**
     * 验证码图片宽度
     */
    private int width = 67;
    /**
     * 验证码图片高度
     */
    private int height = 23;
}
