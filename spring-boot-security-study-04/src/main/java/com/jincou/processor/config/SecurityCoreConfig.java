package com.jincou.processor.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
  * @Description: 把ValidateCodeProperties变成配置类
  *
  * @author xub
  * @date 2020/4/27 下午1:54
  */
@Configuration
@EnableConfigurationProperties(ValidateCodeProperties.class)
public class SecurityCoreConfig {


}
