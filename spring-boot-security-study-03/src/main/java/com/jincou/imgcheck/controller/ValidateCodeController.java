package com.jincou.imgcheck.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 *
 * @author xub
 * @date 2020/4/25 上午9:19
 */
@RestController
public class ValidateCodeController {

    static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private static final String FORMAT_NAME = "JPEG";

    private final ValidateCodeGenerator imageCodeGenerator;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    public ValidateCodeController(ValidateCodeGenerator imageCodeGenerator) {
        this.imageCodeGenerator = imageCodeGenerator;
    }

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 第一步：根据请求生成一个图形验证码对象
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        // 第二步：将图形验证码对象存到session中,第一个参数可以从传入的请求中获取session
        sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY, imageCode);
        // 第三步：将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), FORMAT_NAME, response.getOutputStream());
    }

}
