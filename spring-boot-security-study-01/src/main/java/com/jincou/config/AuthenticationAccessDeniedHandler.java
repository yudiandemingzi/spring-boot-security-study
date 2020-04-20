package com.jincou.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户未认证异常捕获
 */
@Slf4j
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        // response.sendRedirect("/success");
        resp.setContentType("application/json;charset=utf-8");
        out.write("{\"status\":\"err\",\"msg\":\"权限不足,请联系管理员!\"}");
        out.flush();
        out.close();
        log.info("权限不足");
        out.flush();
        out.close();
    }
}
