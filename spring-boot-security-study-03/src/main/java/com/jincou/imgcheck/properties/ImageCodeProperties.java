package com.jincou.imgcheck.properties;

import lombok.Data;

/**
  * @Description: 图形验证码的默认配置
  *
  * @author xub
  * @date 2020/4/24 下午11:50
  */
@Data
public class ImageCodeProperties {

    /**
     * 验证码宽度
     */
    private int width = 67;
    /**
     * 验证码高度
     */
    private int height = 23;
    /**
     * 验证码长度
     */
    private int length = 4;
    /**
     * 验证码过期时间
     */
    private int expireIn = 60;

    /**
     * 需要验证码的请求url字符串，用英文逗号隔开
     */
    private String url = "/login";

}
