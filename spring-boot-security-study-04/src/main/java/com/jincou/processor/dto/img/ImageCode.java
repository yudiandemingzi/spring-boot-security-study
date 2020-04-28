package com.jincou.processor.dto.img;

import com.jincou.processor.dto.ValidateCode;
import lombok.Data;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
  * @Description: 图形验证码需要新增一个属性 图片背景
  *
  * @author xub
  * @date 2020/4/27 上午9:42
  */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.image = image;
    }
}
