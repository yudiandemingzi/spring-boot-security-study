package com.jincou.imgcheck.config;

import com.jincou.imgcheck.properties.ImageCodeProperties;
import com.jincou.imgcheck.service.ValidateCodeGeneratorService;
import com.jincou.imgcheck.service.impl.ImageCodeGeneratorServiceImpl;
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
    public ValidateCodeGeneratorService imageCodeGenerator() {
        ImageCodeGeneratorServiceImpl imageCodeGenerator = new ImageCodeGeneratorServiceImpl();
        imageCodeGenerator.setImageCodeProperties(imageCodeProperties());
        return imageCodeGenerator;
    }

    @Bean
    public ImageCodeProperties imageCodeProperties() {
        return new ImageCodeProperties();
    }
}
