package com.jincou.processor.controller;


import com.jincou.processor.service.ValidateCodeProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Description: TODO
 *
 * @author xub
 * @date 2020/4/25 上午9:19
 */
@RestController
@Slf4j
public class ValidateCodeController {


    @Autowired
    private  Map<String, ValidateCodeProcessorService> validateCodeProcessorMap;


    @RequestMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        if(StringUtils.equalsAny(type, "image", "sms")){
            log.info("type类型错误 type={}",type);
        };

        //根据type获取具体的实现类
        ValidateCodeProcessorService validateCodeProcessorService = validateCodeProcessorMap.get(type.concat(ValidateCodeProcessorService.CODE_PROCESSOR));
        validateCodeProcessorService.processor(new ServletWebRequest(request, response));

    }

}
