package com.jincou.processor.service;

import com.jincou.processor.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @Description: 生成验证码接口
 *
 * @author xub
 * @date 2020/4/27 上午9:46
 */
public interface ValidateCodeGeneratorService {

    /**
     * 这个常量有用
     */
    String CODE_GENERATOR = "CodeGenerator";

    /**
     * 生成验证码
     * 具体是图片验证码 还是短信验证码就需要对应的实现类
     */
    ValidateCode generate(ServletWebRequest request);
}
