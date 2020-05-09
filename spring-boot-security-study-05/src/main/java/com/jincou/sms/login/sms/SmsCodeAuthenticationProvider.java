package com.jincou.sms.login.sms;

import com.jincou.sms.login.dto.sms.SmsCode;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


/**
 * @Description: 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 *
 * @author xub
 * @date 2020/5/8 下午5:53
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 处理session工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_SMS";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();

        checkSmsCode(mobile);

        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkSmsCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 从session中获取图片验证码
        SmsCode smsCodeInSession = (SmsCode) sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY_PREFIX);
        String inputCode = request.getParameter("smsCode");
        if(smsCodeInSession == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        String mobileSsion = smsCodeInSession.getMobile();
        if(!Objects.equals(mobile,mobileSsion)) {
            throw new BadCredentialsException("手机号码不正确");
        }

        String codeSsion = smsCodeInSession.getCode();
        if(!Objects.equals(codeSsion,inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}