package com.jincou.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
  * @Description: 不管短信验证码和图形验证码 code和过期时间都是必须的
  *
  * @author xub
  * @param
  */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
