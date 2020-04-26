package jincou.imgcheck.service;


import com.jincou.entity.Roles;
import com.jincou.entity.RolesUser;
import com.jincou.entity.User;
import com.jincou.mapper.RolesMapper;
import com.jincou.mapper.RolesUserMapper;
import com.jincou.mapper.UserMapper;
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
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesUserMapper rolesUserMapper;

    @Autowired
    private RolesMapper rolesMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取用户信息
        User user = userMapper.findOneByUsername(userName);
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
