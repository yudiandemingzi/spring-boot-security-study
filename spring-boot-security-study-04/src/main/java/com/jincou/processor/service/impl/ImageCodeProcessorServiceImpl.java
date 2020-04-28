package com.jincou.processor.service.impl;

import com.jincou.processor.dto.img.ImageCode;
import com.jincou.processor.service.AbstractValidateCodeProcessorService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Description: 图形验证码 发送具体实现类
 *
 * @author xub
 * @date 2020/4/27 上午11:30
 */
@Component("imageCodeProcessorService")
public class ImageCodeProcessorServiceImpl extends AbstractValidateCodeProcessorService<ImageCode> {

    private static final String FORMAT_NAME = "JPEG";

    /**
     * 发送图形验证码，将其写到相应中
     *
     * @param request   ServletWebRequest实例对象
     * @param imageCode 验证码
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), FORMAT_NAME, request.getResponse().getOutputStream());
    }
}
