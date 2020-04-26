package com.jincou.imgcheck.service;

import com.jincou.imgcheck.properties.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
  * @Description: 抽出一个类
  *
  * @author xub
  * @date 2020/4/24 下午11:58
  */
public interface ValidateCodeGeneratorService {

    /**
     * 生成图片验证码
     *
     * @param request 请求
     * @return ImageCode实例对象
     */
    ImageCode generate(ServletWebRequest request);
}
