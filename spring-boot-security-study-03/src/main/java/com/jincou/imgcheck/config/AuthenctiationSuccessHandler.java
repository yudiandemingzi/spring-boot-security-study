package jincou.imgcheck.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xub
 * @Description: 自定义登陆成功处理类
 * @date 2020/3/13 下午5:46
 */
@Slf4j
@Component
public class AuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功 用户名 = {}", authentication.getName());
        log.info("登陆成功 成功信息 = {}", JSONObject.toJSONString(authentication));
        //往sesson 放信息
        String name = authentication.getName();
        HttpSession session = request.getSession();
        session.setAttribute("user", name);
       // response.sendRedirect("/success");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
        out.flush();
        out.close();
    }

}
