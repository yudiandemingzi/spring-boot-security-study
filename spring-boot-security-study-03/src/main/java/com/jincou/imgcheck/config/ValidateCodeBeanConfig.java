package com.jincou.imgcheck.validate.code;

import com.jincou.imgcheck.properties.ImageCodeProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
  * @Description: 初始化一些bean对象
  *
  * @author xub
  * @date 2020/4/25 上午10:17
  */
@Configuration
public class ValidateCodeBeanConfig {


    @Bean
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setImageCodeProperties(imageCodeProperties());
        return imageCodeGenerator;
    }

    @Bean
    public ImageCodeProperties imageCodeProperties() {
        return new ImageCodeProperties();
    }
}
