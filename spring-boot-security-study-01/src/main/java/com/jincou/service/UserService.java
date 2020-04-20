package com.jincou.service;



import com.google.common.collect.Lists;
import com.jincou.entity.Role;
import com.jincou.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author xub
 * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法，在执行登录的过程中，这个方法将根据用户名去查找用户，
 * 如果用户不存在，则抛出UsernameNotFoundException异常
 * @date 2020/3/13 下午3:46
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //TODO 正常应该查询数据库获取用户和用户的权限
//        User user = userMapper.loadUserByUsername(userName);
//        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
//        user.setRoles(roles);
        log.info("登陆用户名： {}", userName);
        //通过用户名查询到的密码 密码肯定是加密过的 这里明文密码是 123456
        String password = "e10adc3949ba59abbe56e057f20f883e";
        //用户对应权限
        List<Role> roles = Lists.newArrayList(new Role(1L, "教师"), new Role(2L, "学生"));
        User user = new User(userName, password, roles);
        return user;
    }




}
