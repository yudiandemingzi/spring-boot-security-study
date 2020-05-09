package com.jincou.sms.login.controller;


import com.jincou.sms.login.util.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试Controller
 */
@RestController
public class TestController {

    /**
     * 1、不需要登陆就可以访问
     */
    @RequestMapping(value = "/no-login")
    public ServiceResponse noLogin() {

        return ServiceResponse.success("欢迎访问不需要登陆接口");
    }

    /**
     * 2、只登陆，不许认证接口
     */
    @RequestMapping(value = "/no-authorize")
    public ServiceResponse needAuthorize(){

        return ServiceResponse.success("登陆了 不用授权");
    }

    /**
     * 3、登陆 + 相关认证接口
     */
    @RequestMapping(value = "/need-authorize")
    public ServiceResponse noAuthorize() {

        return ServiceResponse.success("登陆+授权成功");
    }

    /**
     * @Description: 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     *
     */
    @RequestMapping("/login_page")
    public ServiceResponse loginPage() {
        return  ServiceResponse.failure("001", "尚未登录，请登录!");
    }
}
