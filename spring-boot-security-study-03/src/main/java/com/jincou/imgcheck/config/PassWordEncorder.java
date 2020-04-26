package jincou.imgcheck.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @Description: 自定义密码校验器
 *
 * @author xub
 * @date 2020/3/13 下午4:56
 */
@Slf4j
@Component
public class PassWordEncorder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        log.info("自定义密码校验器 encode = {}" , DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String password) {
        String pwd = charSequence.toString();
        log.info("前端传来  明文密码 = {} " , pwd);
        log.info("前端传来  加密密码 = {} " , DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
        log.info("后端校验  加密密码 = {} " , password);
        return password.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
    }
}
