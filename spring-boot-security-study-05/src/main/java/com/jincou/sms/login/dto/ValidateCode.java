package com.jincou.sms.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
