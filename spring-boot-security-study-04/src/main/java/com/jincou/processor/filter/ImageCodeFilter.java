package com.jincou.processor.filter;


import com.jincou.processor.config.AuthenctiationFailHandler;
import com.jincou.processor.config.ValidateCodeProperties;
import com.jincou.processor.dto.img.ImageCode;
import com.jincou.processor.exception.ValidateCodeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.jincou.processor.service.ValidateCodeProcessorService.SESSION_KEY_PREFIX;


/**
  * @Description: 这个才是整个核心 这个过滤器肯定要在验证账号密码之前进行验证过滤
  * filter都默认继承OncePerRequestFilter
  * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
  *
  * @author xub
  * @date 2020/4/25 上午10:25
  */
@Data
@Slf4j
@Component
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {


    private static final String IMAGE_SESSION_KEY = SESSION_KEY_PREFIX + "IMAGE";

    /**
     * 失败处理器
     */
    @Autowired
    private AuthenctiationFailHandler authenctiationFailHandler;

    /**
     * 验证码属性类
     */
    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    /**
     * 存放需要走验证码请求url
     */
    private Set<String> urls = new HashSet<>();

    /**
     * 处理session工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 正则配置工具
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 在初始化bean的时候都会执行该方法
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.split(validateCodeProperties.getImage().getUrl(), ",");
        // 登录的链接是必须要进行验证码验证的
        urls.addAll(Arrays.asList(configUrls));
    }

    /**
     * 拦截请求进来的方法。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            // 如果实际访问的URL可以与用户在imageCodeProperties中url配置的相同，那么就进行验证码校验
            log.info("request.getRequestURI = {}",request.getRequestURI());
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        //说明需要校验
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenctiationFailHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        //进入下一个过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验逻辑
     *
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 从session中获取图片验证码
        ImageCode imageCodeInSession = (ImageCode) sessionStrategy.getAttribute(request, IMAGE_SESSION_KEY);

        // 从请求中获取用户填写的验证码
        String imageCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(imageCodeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == imageCodeInSession) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCodeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, IMAGE_SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        log.info("session中获取的验证码={},sessionId ={}",imageCodeInSession.getCode(),request.getSessionId());
        log.info("登陆操作传来的验证码={}",imageCodeInRequest);
        if (!StringUtils.equalsIgnoreCase(imageCodeInRequest, imageCodeInSession.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        // 验证成功，删除session中的验证码
        sessionStrategy.removeAttribute(request, IMAGE_SESSION_KEY);
    }
}
