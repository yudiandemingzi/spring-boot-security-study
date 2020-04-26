package com.jincou.imgcheck.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author xub
 * @Description: 图片验证码信息 由三部分组成
 *
 * 1、图片信息（长、宽、背景色等等）
 * 2、code就是真正的验证码，用来验证用）
 * 3、该验证码的有效时间
 *
 * @date 2020/4/24 下午11:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;


    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 校验是否获取
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
