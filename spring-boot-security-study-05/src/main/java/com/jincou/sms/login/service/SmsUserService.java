package com.jincou.sms.login.service;



import com.jincou.sms.login.entity.Roles;
import com.jincou.sms.login.entity.RolesUser;
import com.jincou.sms.login.entity.User;
import com.jincou.sms.login.mapper.RolesMapper;
import com.jincou.sms.login.mapper.RolesUserMapper;
import com.jincou.sms.login.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author xub
 * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法，在执行登录的过程中，这个方法将根据用户名去查找用户，
 * 如果用户不存在，则抛出UsernameNotFoundException异常
 * @date 2020/3/13 下午3:46
 */
@Service
@Slf4j
public class SmsUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesUserMapper rolesUserMapper;

    @Autowired
    private RolesMapper rolesMapper;

    /**
     * 手机号查询用户
     */
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        log.info("手机号查询用户，手机号码 = {}",mobile);
        //TODO 这里我没有写通过手机号去查用户信息的sql,因为一开始我建user表的时候，没有建mobile字段，现在我也不想临时加上去
        //TODO 所以这里暂且写死用用户名去查询用户信息（理解就好）
        User user = userMapper.findOneByUsername("小小");
        if (user == null) {
            throw new UsernameNotFoundException("未查询到用户信息");
        }
        //获取用户关联角色信息 如果为空说明用户并未关联角色
        List<RolesUser> userList = rolesUserMapper.findAllByUid(user.getId());
        if (CollectionUtils.isEmpty(userList)) {
            return user;
        }
        //获取角色ID集合
        List<Integer> ridList = userList.stream().map(RolesUser::getRid).collect(Collectors.toList());
        List<Roles> rolesList = rolesMapper.findByIdIn(ridList);
        //插入用户角色信息
        user.setRoles(rolesList);
        return user;
    }


}
